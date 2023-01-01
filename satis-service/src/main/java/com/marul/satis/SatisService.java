package com.marul.satis;

import com.marul.dto.mail.MailGondermeDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.satis.SatisDto;
import com.marul.dto.satis.SatisResponseDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import com.marul.integration.MusteriServiceIntegration;
import com.marul.integration.RaporServiceIntegration;
import com.marul.integration.StokServiceIntegration;
import com.marul.satis.dto.SatisInsertDto;
import com.marul.satis.dto.SonSatisOzetiDto;
import com.marul.urun.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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

    public List<SatisDto> findByMusteriId(Long musteriId) {
        List<Satis> satisList = satisRepository.findByMusteriId(musteriId);
        if (satisList.isEmpty()) {
            throw new BulunamadiException("%s musteriId ile hiç satış bulunamadı", musteriId.toString());
        }
        return satisMapper.getDtoList(satisList);
    }

    public List<SatisResponseDto> save(@Valid SatisInsertDto satisInsertDto) {
        Long musteriId = satisInsertDto.getMusteriId();
        List<Satis> satisList = satisInsertDto.getSatisDtoList()
                .stream().map(satisDto -> {
                    Long urunId = satisDto.getUrunId();
                    musteriKontrolu(musteriId);
                    urunKontrolu(urunId);
                    Satis satis = new Satis();
                    satis.setUrunId(urunId);
                    satis.setMusteriId(musteriId);
                    satis.setSatilanAdet(satisDto.getSatilanAdet());
                    satis.setSatisZamani(LocalDateTime.now());
                    return satisRepository.save(satis);
                }).collect(Collectors.toList());
        stokGuncelle(satisInsertDto);
        kafkayaBildir(satisInsertDto);
        return satisMapper.getResponseDtoList(satisList);
    }

    private void kafkayaBildir(SatisInsertDto satisInsertDto) {
        kafkaTemplate.send("marul-satis", satisInsertDto);
    }


    public List<SonSatisOzetiDto> sonSatislariGetir() {
        List<Satis> satis = satisRepository.findAll(Pageable.ofSize(20)).toList();
        return satis.stream()
                .map(satisDto -> {
                    Long urunId = satisDto.getUrunId();
                    Long musteriId = satisDto.getMusteriId();
                    MusteriDto musteriDto = musteriServiceIntegration.findById(musteriId);
                    UrunDto urunDto = urunService.findById(urunId);
                    return SonSatisOzetiDto.builder()
                            .musteriAdi(musteriDto.getMusteriAdi())
                            .urunAdi(urunDto.getUrunAdi())
                            .satisTutari(urunDto.getFiyat().multiply(BigDecimal.valueOf(satisDto.getSatilanAdet())))
                            .build();
                }).collect(Collectors.toList());
    }

    public String findUrunAdiBySatisId(Long satisId) {
        return satisRepository.findUrunAdiBySatisId(satisId)
                .orElseThrow(() -> new BulunamadiException("%s satis id ile urun bulunamadı.", satisId.toString()));
    }

    public List<SatisDto> baslangicVeBitisZamaninaGoreSatisGetir(LocalDateTime baslangiZamani, LocalDateTime bitisZamani) {
        List<Satis> satisList = satisRepository.haftalikSatisiGetir(baslangiZamani, bitisZamani);
        return satisMapper.getDtoList(satisList);
    }

    private void stokGuncelle(SatisInsertDto satisInsertDto) {
        satisInsertDto.getSatisDtoList()
                .forEach(satisDto -> {
                    long urunId = satisDto.getUrunId();
                    long satilanAdet = satisDto.getSatilanAdet();
                    stokServiceIntegration.stokGuncelle(urunId, satilanAdet);
                });
    }

    private void musteriKontrolu(Long musteriId) {
        boolean musteriBulunduMu = musteriServiceIntegration.existsById(musteriId);
        if (!musteriBulunduMu) {
            throw new BulunamadiException("%s id ile müşteri bulunamadı", musteriId.toString());
        }
    }

    private void urunKontrolu(Long urunId) {
        boolean urunBulunduMu = urunService.existsById(urunId);
        if (!urunBulunduMu) {
            throw new BulunamadiException("%s id ile ürün bulunamadı", urunId.toString());
        }
    }

    public byte[] satisRaporuGetir(Long musteriId) {

        MusteriDto musteriDto = musteriServiceIntegration.findById(musteriId);

        List<RaporDto> raporDtoList = findByMusteriId(musteriId)
                .stream()
                .map(satisDto -> {
                    RaporDto raporDto = new RaporDto();
                    Long urunId = satisDto.getUrunId();
                    Long satilanAdet = satisDto.getSatilanAdet();
                    raporDto.setMiktar(satilanAdet);
                    UrunDto urunDto = urunService.findById(urunId);
                    raporDto.setTutar(urunDto.getFiyat().multiply(BigDecimal.valueOf(satilanAdet)));
                    raporDto.setUrunAdi(urunDto.getUrunAdi());
                    return raporDto;
                })
                .collect(Collectors.toList());


        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto();
        Map<String, Object> raporParametreleri = new HashMap<>();
        raporParametreleri.put("musteriAdi", musteriDto.getMusteriAdi());
        raporOlusturmaDto.setRaporAdi("satis-faturasi.jrxml");
        raporOlusturmaDto.setRaporParametreleri(raporParametreleri);
        raporOlusturmaDto.setRaporDtoList(raporDtoList);

        return raporServiceIntegration.generateSimpleReport(raporOlusturmaDto);
    }

    @KafkaListener(
            topics = "marul-satis",
            groupId = "group-id2"
    )
    public void faturaOlusturVeMailAt(SatisInsertDto satisInsertDto) {
        Long musteriId = satisInsertDto.getMusteriId();
        MusteriDto musteriDto = musteriServiceIntegration.findById(musteriId);
        byte[] satisFaturasi = satisRaporuGetir(musteriId);
        MailGondermeDto mailGondermeDto = new MailGondermeDto();
        mailGondermeDto.setInputStream(satisFaturasi);
        mailGondermeDto.setEmailTo(musteriDto.getEmail());
        mailGondermeDto.setBody("Sayın " + musteriDto.getMusteriAdi() + ",\nSatış raporunuz ekte sunulmuştur.");
        mailGondermeDto.setSubject("Marul satış raporu");
        mailKafkaTemplate.send("marul-mail", mailGondermeDto);
    }
}
