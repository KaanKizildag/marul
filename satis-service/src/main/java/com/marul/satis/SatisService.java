package com.marul.satis;

import com.marul.dto.MailGondermeDto;
import com.marul.dto.SatisDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.satis.KasaHareketiInsertDto;
import com.marul.dto.satis.KategoriSatisAnalizDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import com.marul.kasahareketi.KasaHareketiService;
import com.marul.kategori.KategoriDto;
import com.marul.kategori.KategoriService;
import com.marul.satis.dto.SonSatisOzetiDto;
import com.marul.urun.StokFeignClient;
import com.marul.urun.UrunService;
import com.marul.util.ResultDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
    private final MusteriFeignClient musteriFeignClient;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final StokFeignClient stokFeignClient;
    private final SatisMapper satisMapper;
    private final UrunService urunService;
    private final KategoriService kategoriService;
    private final KasaHareketiService kasaHareketiService;
    private final KafkaTemplate<String, MailGondermeDto> kafkaTemplate;

    public List<SatisDto> findAll() {
        List<Satis> satisList = satisRepository.findAll();
        return satisList.stream().map(satisMapper::getDto)
                .collect(Collectors.toList());
    }

    public List<SatisDto> findByMusteriId(Long musteriId) {
        List<Satis> satisList = satisRepository.findByMusteriId(musteriId);
        if (satisList.isEmpty()) {
            throw new BulunamadiException("%s musteriId ile hi?? sat???? bulunamad??", musteriId.toString());
        }
        return satisMapper.getDtoList(satisList);
    }


    public SatisDto save(SatisDto satisDto) {
        Long musteriId = satisDto.getMusteriId();
        Long urunId = satisDto.getUrunId();
        musteriKontrolu(musteriId);
        urunKontrolu(urunId);
        stokGuncelle(satisDto, urunId);
        kasaHareketiOlustur(satisDto, urunId);
        Satis satis = satisMapper.getEntity(satisDto);
        satis.setSatisZamani(LocalDateTime.now());
        satis = this.satisRepository.save(satis);
        mailGonder();
        return satisMapper.getDto(satis);
    }

    private void mailGonder() {
        MailGondermeDto mailGondermeDto = new MailGondermeDto();
        mailGondermeDto.setEmailTo("huseyinkaan.kizildag@gmail.com");
        mailGondermeDto.setBody("sat???? yap??ld??");
        mailGondermeDto.setSubject("Marul");
        kafkaTemplate.send("marul-mail", mailGondermeDto);
    }

    private void kasaHareketiOlustur(SatisDto satisDto, Long urunId) {
        BigDecimal satislanAdet = BigDecimal.valueOf(satisDto.getSatilanAdet());
        BigDecimal fiyat = urunService.findById(urunId).getFiyat();

        KasaHareketiInsertDto kasaHareketiInsertDto = new KasaHareketiInsertDto();
        kasaHareketiInsertDto.setAciklama("??r??n sat??????");
        kasaHareketiInsertDto.setTutar(fiyat.multiply(satislanAdet));

        kasaHareketiService.save(kasaHareketiInsertDto);
    }

    public List<SatisDto> save(List<SatisDto> satisDtoList) {
        return satisDtoList.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    public List<SonSatisOzetiDto> sonSatislariGetir() {
        List<Satis> satis = satisRepository.findAll(Pageable.ofSize(20)).toList();
        return satis.stream()
                .map(satisDto -> {
                    Long urunId = satisDto.getUrunId();
                    Long musteriId = satisDto.getMusteriId();
                    MusteriDto musteriDto = ResultDecoder.getDataResult(musteriFeignClient.findById(musteriId));
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
                .orElseThrow(() -> new BulunamadiException("%s satis id ile urun bulunamad??.", satisId.toString()));
    }

    public List<SatisDto> baslangicVeBitisZamaninaGoreSatisGetir(LocalDateTime baslangiZamani, LocalDateTime bitisZamani) {
        List<Satis> satisList = satisRepository.haftalikSatisiGetir(baslangiZamani, bitisZamani);
        return satisMapper.getDtoList(satisList);
    }

    public Map<String, BigDecimal> haftalikSatisToMap(List<KategoriSatisAnalizDto> kategoriSatisAnalizDtoList) {
        Map<String, BigDecimal> haftalikSatisMap = new HashMap<>();
        kategoriSatisAnalizDtoList.forEach(
                kategoriSatisAnalizDto -> {
                    String kategoriAdi = kategoriSatisAnalizDto.getKategoriAdi();
                    BigDecimal toplamSatisTutari = kategoriSatisAnalizDto.getToplamSatisTutari();
                    if (!haftalikSatisMap.containsKey(kategoriAdi)) {
                        haftalikSatisMap.put(kategoriAdi, toplamSatisTutari);
                    } else {
                        BigDecimal toplamSatis = haftalikSatisMap.get(kategoriAdi)
                                .add(toplamSatisTutari);
                        ;
                        haftalikSatisMap.put(kategoriAdi, toplamSatis);
                    }
                }
        );
        return haftalikSatisMap;
    }

    public Map<String, BigDecimal> haftalikSatislariGetir() {
        LocalDateTime baslangiZamani = LocalDateTime.now().minusDays(7);
        LocalDateTime bitisZamani = LocalDateTime.now();
        List<SatisDto> satisDtoList = baslangicVeBitisZamaninaGoreSatisGetir(baslangiZamani, bitisZamani);
        List<KategoriSatisAnalizDto> kategoriSatisAnalizDtoList = satisDtoList.stream().map(satis ->
                {
                    UrunDto urunDto = urunService.findById(satis.getUrunId());
                    BigDecimal fiyat = urunDto.getFiyat();
                    Long satilanAdet = satis.getSatilanAdet();
                    BigDecimal toplam = fiyat.multiply(BigDecimal.valueOf(satilanAdet));
                    Long kategoriId = urunDto.getKategoriId();
                    KategoriDto kategoriDto = kategoriService.findById(kategoriId);

                    return KategoriSatisAnalizDto.builder()
                            .toplamSatisTutari(toplam)
                            .kategoriAdi(kategoriDto.getKategoriAdi())
                            .build();
                }
        ).collect(Collectors.toList());

        return haftalikSatisToMap(kategoriSatisAnalizDtoList);
    }

    private void stokGuncelle(SatisDto satisDto, Long urunId) {
        Long satilanAdet = satisDto.getSatilanAdet();
        ResultDecoder.utilServiceCheck(stokFeignClient.stokGuncelle(urunId, satilanAdet));
    }

    private void musteriKontrolu(Long musteriId) {
        boolean musteriBulunduMu = ResultDecoder.getDataResult(musteriFeignClient.existsById(musteriId));
        if (!musteriBulunduMu) {
            throw new BulunamadiException("%s id ile m????teri bulunamad??", musteriId.toString());
        }
    }

    private void urunKontrolu(Long urunId) {
        boolean urunBulunduMu = urunService.existsById(urunId);
        if (!urunBulunduMu) {
            throw new BulunamadiException("%s id ile ??r??n bulunamad??", urunId.toString());
        }
    }

    public byte[] satisRaporuGetir(Long musteriId) {

        MusteriDto musteriDto = ResultDecoder.getDataResult(musteriFeignClient.findById(musteriId));

        List<RaporDto> raporDtoList = findByMusteriId(musteriId).stream()
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

        return ResultDecoder.getDataResult(raporServiceFeignClient.generateSimpleReport(raporOlusturmaDto));
    }

}
