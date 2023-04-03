package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.NotFoundException;
import com.marul.exception.YeterliStokYokException;
import com.marul.stokservice.integration.SatisServiceIntegration;
import com.marul.stokservice.stok.dto.KritikStokDurumDto;
import com.marul.stokservice.stokhareketi.StokHarektiService;
import com.marul.stokservice.stokhareketi.dto.StokHareketiDto;
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
    private final SatisServiceIntegration satisServiceIntegration;
    private final StokMapper stokMapper;
    private final StokHarektiService stokHareketiService;

    private final int KRITIK_STOK_LIMIT = 10;

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
                .orElseThrow(() -> new NotFoundException(String.format("%s id ile ürün bulunamadı", urunId)));

        long stokAdet = stok.getAdet();

        if (!yeterliStokVarMi(urunId, satilanAdet)) {
            String errorMsg = String.format("%d idli üründen yeterli stok yok stok durumu: %d, satılmak istenen %d.",
                    urunId, stokAdet, satilanAdet);
            log.error(errorMsg);
            throw new YeterliStokYokException(errorMsg);
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

    private boolean yeterliStokVarMi(Long urunId, Long satilanAdet) {
        Stok stok = stokRepository.findByUrunId(urunId)
                .orElseThrow(() -> new NotFoundException(String.format("%s id ile ürün bulunamadı", urunId)));
        long stokAdet = stok.getAdet();
        return stokAdet >= satilanAdet;
    }

    public StokDto findById(Long stokId) {
        return stokRepository.findById(stokId)
                .map(stokMapper::getDto)
                .orElseThrow(() -> new NotFoundException("Stok bulunamadı stokId: %d", stokId));
    }

    public List<KritikStokDurumDto> findAllByOrOrderByAdetAsc() {
        List<Stok> stokList = stokRepository.findAllByOrOrderByAdetAsc(PageRequest.of(0, 20));
        return stokList
                .stream()
                .map(this::getKritikStokDurumDto)
                .collect(Collectors.toList());

    }

    private KritikStokDurumDto getKritikStokDurumDto(Stok stok) {
        UrunDto urunDto = satisServiceIntegration.findUrunById(stok.getUrunId());
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
                .orElseThrow(() -> new NotFoundException("%s ürün id ile stok bulunamadı", urunId.toString()));

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
