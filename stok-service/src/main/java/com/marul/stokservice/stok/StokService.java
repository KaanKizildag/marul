package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import com.marul.exception.YeterliStokYokException;
import com.marul.stokservice.stok.dto.KritikStokDurumDto;
import com.marul.stokservice.stokhareketi.StokHareketiDto;
import com.marul.stokservice.stokhareketi.StokHarektiService;
import com.marul.util.ResultDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StokService {

    private final StokRepository stokRepository;
    private final SatisFeignClient satisFeignClient;
    private final StokMapper stokMapper;
    private final StokHarektiService stokHareketiService;

    private final int KRITIK_STOK_LIMIT = 10;

    public boolean yeterliStokVarMi(Long urunId, Long stok) {
        boolean urunVarMi = ResultDecoder.getDataResult(satisFeignClient.existsUrunById(urunId));
        if (!urunVarMi) {
            log.error("{} id ile ürün bulunamadı", urunId);
            throw new BulunamadiException("%s id ile ürün bulunamadı", urunId.toString());
        }
        return stokRepository.yeterliStokVarMi(urunId, stok);
    }

    public StokDto save(StokKaydetDto stokKaydetDto) {
        Stok stok = stokMapper.getEntity(stokKaydetDto);
        stok = stokRepository.save(stok);
        log.info("stok kaydedildi.");
        StokHareketiDto stokHareketiDto = StokHareketiDto.builder()
                .hareketZamani(LocalDateTime.now())
                .aciklama(stokKaydetDto.getAciklama())
                .satisMi(false)
                .miktar(stokKaydetDto.getAdet())
                .stokId(stok.getId())
                .build();
        stokHareketiService.save(stokHareketiDto);
        return stokMapper.getDto(stok);
    }

//
// public StokDto save(StokGuncelleDto stokKaydetDto) {
//        Stok stok = stokMapper.getEntity(stokKaydetDto);
//        stok = stokRepository.save(stok);
//        log.info("stok kaydedildi.");
//        StokHareketiDto stokHareketiDto = StokHareketiDto.builder()
//                .hareketZamani(LocalDateTime.now())
//                .aciklama(stokKaydetDto.getAciklama())
//                .satisMi(false)
//                .miktar(stokKaydetDto.getAdet())
//                .stokId(stok.getId())
//                .build();
//        stokHareketiService.save(stokHareketiDto);
//        return stokMapper.getDto(stok);
//    }

    @Deprecated
    /**
     * @deprecated stok güncelleme dto ile güncellenmeli
     */
    public boolean stokGuncelle(Long urunId, Long satilanAdet) {
        Stok stok = stokRepository.findByUrunId(urunId)
                .orElseThrow(() -> new BulunamadiException("%s id ile ürün bulunamadı", urunId.toString()));
        long stokAdet = stok.getAdet();

        boolean yeterliStokVarMi = yeterliStokVarMi(urunId, satilanAdet);
        if (!yeterliStokVarMi) {
            log.error("yeterli stok yok stok durumu: {}, satilmak istenen {}", stokAdet, satilanAdet);
            throw new YeterliStokYokException("%d idli üründen yeterli stok yok stok durumu: %d, satilmak istenen %d.", urunId, stokAdet, satilanAdet);
        }

        stok.setAdet(stokAdet - satilanAdet);
        stok = stokRepository.save(stok);

        StokHareketiDto stokHareketiDto = StokHareketiDto.builder()
                .hareketZamani(LocalDateTime.now())
                .aciklama("Stok güncelleme")
                .satisMi(satilanAdet > 0)
                .miktar(satilanAdet)
                .stokId(stok.getId())
                .build();
        stokHareketiService.save(stokHareketiDto);

        return stok.getId() != null;
    }

    public Long findUrunIdByStokId(Long stokId) {
        return stokRepository.findUrunIdById(stokId)
                .orElseThrow(() -> new BulunamadiException("Stok Id ile ürün bulunamadı"));
    }

    public List<KritikStokDurumDto> findAllByOrOrderByAdetAsc() {
        List<Stok> stokList = stokRepository.findAllByOrOrderByAdetAsc(PageRequest.of(0, 20));
        return stokList
                .stream()
                .map(this::getKritikStokDurumDto)
                .collect(Collectors.toList());

    }

    private KritikStokDurumDto getKritikStokDurumDto(Stok stok) {
        UrunDto urunDto = ResultDecoder.getDataResult(satisFeignClient.findById(stok.getUrunId()));
        return KritikStokDurumDto.builder()
                .adet(stok.getAdet())
                .urunId(stok.getUrunId())
                .urunAdi(urunDto.getUrunAdi())
                .kategoriAdi(urunDto.getKategoriAdi())
                .kritikMi(stok.getAdet() < KRITIK_STOK_LIMIT)
                .build();
    }

    public void urunIdIleStokSil(Long urunId) {
        Stok stok = stokRepository.findStokByUrunId(urunId)
                .orElseThrow(() -> new BulunamadiException("%s ürün id ile stok bulunamadı", urunId.toString()));

        stokHareketiService.save(StokHareketiDto.builder()
                .stokId(stok.getId())
                .hareketZamani(LocalDateTime.now())
                .miktar(stok.getAdet())
                .aciklama("Ürün silme")
                .satisMi(false)
                .build());

        stokRepository.delete(stok);
    }
}
