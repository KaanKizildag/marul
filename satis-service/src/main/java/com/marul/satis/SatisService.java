package com.marul.satis;

import com.marul.dto.SatisDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import com.marul.urun.StokFeignClient;
import com.marul.urun.UrunService;
import com.marul.util.ResultDecoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SatisService {

    private final SatisRepository satisRepository;
    private final MusteriFeignClient musteriFeignClient;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final StokFeignClient stokFeignClient;
    private final SatisMapper satisMapper;
    private final UrunService urunService;

    public List<SatisDto> findAll() {
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

    public SatisDto save(SatisDto satisDto) {
        Long musteriId = satisDto.getMusteriId();
        Long urunId = satisDto.getUrunId();
        musteriKontrolu(musteriId);
        urunKontrolu(urunId);
        stokGuncelle(satisDto, urunId);
        Satis satis = satisMapper.getEntity(satisDto);
        satis = this.satisRepository.save(satis);
        return satisMapper.getDto(satis);
    }

    public String findUrunAdiBySatisId(Long satisId) {
        return satisRepository.findUrunAdiBySatisId(satisId)
                .orElseThrow(() -> new BulunamadiException("%s satis id ile urun bulunamadı.", satisId.toString()));
    }

    private void stokGuncelle(SatisDto satisDto, Long urunId) {
        Long satilanAdet = satisDto.getSatilanAdet();
        ResultDecoder.utilServiceCheck(stokFeignClient.stokGuncelle(urunId, satilanAdet));
    }

    private void musteriKontrolu(Long musteriId) {
        boolean musteriBulunduMu = ResultDecoder.getDataResult(musteriFeignClient.existsById(musteriId));
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
