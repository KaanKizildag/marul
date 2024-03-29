package com.marul.satis;

import com.marul.dto.mail.MailGondermeDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.satis.SatisDto;
import com.marul.dto.satis.SatisResponseDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.NotFoundException;
import com.marul.integration.MusteriServiceIntegration;
import com.marul.integration.RaporServiceIntegration;
import com.marul.integration.StokServiceIntegration;
import com.marul.satis.dto.SatisInsertDto;
import com.marul.satis.dto.SatisUrunDto;
import com.marul.satis.dto.SonSatisOzetiDto;
import com.marul.urun.UrunService;
import com.marul.util.MailUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.marul.kafka.MarulTopicNames.MARUL_MAIL;
import static com.marul.kafka.MarulTopicNames.MARUL_SATIS;

@Service
@RequiredArgsConstructor
@Slf4j
public class SatisService {

    private final SatisRepository satisRepository;
    private final MusteriServiceIntegration musteriServiceIntegration;
    private final RaporServiceIntegration raporServiceIntegration;
    private final StokServiceIntegration stokServiceIntegration;
    private final SatisMapper satisMapper;
    private final UrunService urunService;
    private final KafkaTemplate<String, SatisInsertDto> kafkaTemplate;
    private final KafkaTemplate<String, MailGondermeDto> mailKafkaTemplate;

    public List<SatisResponseDto> findAll() {
        List<Satis> satisList = satisRepository.findAll();
        return satisList.stream().map(satisMapper::getDto)
                .collect(Collectors.toList());
    }

    public List<SatisDto> findByMusteriId(Long musteriId, UUID grupId) {
        List<Satis> satisList = satisRepository.findByMusteriIdAndGrupId(musteriId, grupId);
        if (satisList.isEmpty()) {
            String message = String.format("%d musteriId, grupId %s ile hiç satış bulunamadı", musteriId, grupId);
            log.error(message);
            throw new NotFoundException(message);
        }
        return satisMapper.getDtoList(satisList);
    }

    @Transactional
    public List<SatisResponseDto> save(@Valid SatisInsertDto satisInsertDto) {
        Long musteriId = satisInsertDto.getMusteriId();
        musteriKontrolu(musteriId);

        List<Satis> satisList = new ArrayList<>();
        UUID grupId = UUID.randomUUID();
        satisInsertDto.setGrupId(grupId);
        for (SatisUrunDto satisDto : satisInsertDto.getSatisDtoList()) {
            Long urunId = satisDto.getUrunId();
            UrunDto urunDto = urunService.findById(urunId);

            Satis satis = new Satis();
            satis.setUrunId(urunId);
            satis.setMusteriId(musteriId);
            satis.setSatilanAdet(satisDto.getSatilanAdet());
            satis.setSatisZamani(LocalDateTime.now());
            satis.setSatisFiyati(urunDto.getFiyat());
            satis.setGrup_id(grupId);
            satisList.add(satisRepository.save(satis));
        }

        stokGuncelle(satisInsertDto);
        kafkayaBildir(satisInsertDto);
        faturaOlusturVeMailAt(satisInsertDto);
        return satisMapper.getResponseDtoList(satisList);
    }

    private void kafkayaBildir(SatisInsertDto satisInsertDto) {
        kafkaTemplate.send(MARUL_SATIS, satisInsertDto);
    }


    public List<SonSatisOzetiDto> sonSatislariGetir() {
        Page<Satis> page = satisRepository.findAll(PageRequest.of(0, 20));
        List<Satis> satisList = page.getContent();
        List<SonSatisOzetiDto> result = new ArrayList<>();

        for (Satis satis : satisList) {
            Long urunId = satis.getUrunId();
            Long musteriId = satis.getMusteriId();
            MusteriDto musteriDto = musteriServiceIntegration.findById(musteriId);
            UrunDto urunDto = urunService.findById(urunId);
            BigDecimal satisTutari = urunDto.getFiyat().multiply(BigDecimal.valueOf(satis.getSatilanAdet()));
            SonSatisOzetiDto sonSatisOzetiDto = new SonSatisOzetiDto(musteriDto.getMusteriAdi(), urunDto.getUrunAdi(), satisTutari);
            result.add(sonSatisOzetiDto);
        }

        return result;
    }

    public String findUrunAdiBySatisId(Long satisId) {
        return satisRepository.findUrunAdiBySatisId(satisId)
                .orElseThrow(() -> new NotFoundException("%s satis id ile urun bulunamadı.", satisId.toString()));
    }

    public List<SatisDto> baslangicVeBitisZamaninaGoreSatisGetir(LocalDateTime baslangiZamani, LocalDateTime bitisZamani) {
        List<Satis> satisList = satisRepository.haftalikSatisiGetir(baslangiZamani, bitisZamani);
        return satisMapper.getDtoList(satisList);
    }

    private void stokGuncelle(SatisInsertDto satisInsertDto) {
        for (SatisUrunDto satisDto : satisInsertDto.getSatisDtoList()) {
            long urunId = satisDto.getUrunId();
            long satilanAdet = satisDto.getSatilanAdet();
            stokServiceIntegration.stokGuncelle(urunId, satilanAdet);
        }
    }

    private void musteriKontrolu(Long musteriId) {
        if (!musteriServiceIntegration.existsById(musteriId)) {
            throw new NotFoundException("%s id ile müşteri bulunamadı", musteriId.toString());
        }
    }


    public byte[] generateSalesReport(Long customerId, UUID grupId) {
        MusteriDto customer = musteriServiceIntegration.findById(customerId);

        List<RaporDto> reportData = findByMusteriId(customerId, grupId)
                .stream()
                .map(sales -> {
                    UrunDto product = urunService.findById(sales.getUrunId());
                    BigDecimal totalAmount = product.getFiyat().multiply(BigDecimal.valueOf(sales.getSatilanAdet()));
                    return RaporDto.builder()
                            .urunAdi(product.getUrunAdi())
                            .miktar(sales.getSatilanAdet())
                            .birimFiyati(totalAmount)
                            .birim(product.getBirim().toString())
                            .build();
                })
                .collect(Collectors.toList());

        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("musteriAdi", customer.getMusteriAdi());
        reportParams.put("borc", customer.getBorc());

        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto("satis-faturasi.jrxml", reportData, reportParams);
        return raporServiceIntegration.generateSimpleReport(raporOlusturmaDto);
    }

    @KafkaListener(topics = MARUL_MAIL, groupId = "group-id2")
    public void faturaOlusturVeMailAt(SatisInsertDto satisInsertDto) {
        Long musteriId = satisInsertDto.getMusteriId();
        UUID grupId = satisInsertDto.getGrupId();
        MusteriDto musteriDto = musteriServiceIntegration.findById(musteriId);
        byte[] satisFaturasi = generateSalesReport(musteriId, grupId);
        Map<String, String> parametreMap = new HashMap<>();
        parametreMap.put("musteriAdi", musteriDto.getMusteriAdi());
        parametreMap.put("adSoyad", "Marul uygulaması");
        String body = MailUtils.getMailBodyWithParameters("fatura-mail-sablonu.html", parametreMap);
        MailGondermeDto mailGondermeDto = new MailGondermeDto(musteriDto.getEmail(), body, "Marul satış raporu", satisFaturasi);
        mailGondermeDto.setSubject("Marul satış raporu");
        mailKafkaTemplate.send(MARUL_MAIL, mailGondermeDto);
    }
}
