package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
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

import java.util.List;
import java.util.Optional;
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

    public StokDto save(StokDto stokDto) {
        Long urunId = stokDto.getUrunId();
        Optional<Stok> optionalStok = stokRepository.findByUrunId(urunId);
        if (optionalStok.isPresent()) {
            Stok stok = optionalStok.get();
            Long stokDtoAdet = stokDto.getAdet();
            Long mevcutStok = stok.getAdet();
            long guncelStok = mevcutStok + stokDtoAdet;
            stok.setAdet(guncelStok);
            log.info("{} id ile kayitli ürün bulunduğu için mevcut stok ({}) {} olarak güncellenecek.",
                    urunId, mevcutStok, guncelStok);
            stok = stokRepository.save(stok);
            stokHareketiOlustur(stokDto.getAdet(), stok.getId());
            return stokMapper.getDto(stok);
        }
        Stok stok = stokMapper.getEntity(stokDto);
        stok = stokRepository.save(stok);
        stokHareketiOlustur(stokDto.getAdet(), stok.getId());
        log.info("stok kaydedildi.");
        return stokMapper.getDto(stok);
    }

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
        stokHareketiOlustur(satilanAdet, stok.getId());
        return stok.getId() != null;
    }

    private void stokHareketiOlustur(Long satilanAdet, Long stokId) {
        StokHareketiDto stokHareketiDto = StokHareketiDto.builder()
                .stokId(stokId)
                .miktar(satilanAdet)
                .build();
        stokHareketiService.save(stokHareketiDto);
    }

    public Long findUrunIdByStokId(Long stokId) {
        return stokRepository.findUrunIdById(stokId)
                .orElseThrow(() -> new BulunamadiException("Stok Id ile ürün bulunamadı"));
    }

    public List<KritikStokDurumDto> findAllByOrOrderByAdetAsc() {
        List<Stok> stokList = stokRepository.findAllByOrOrderByAdetAsc(PageRequest.of(0, 5));
        return stokList
                .stream()
                .map(this::getKritikStokDurumDto)
                .collect(Collectors.toList());

    }

    private KritikStokDurumDto getKritikStokDurumDto(Stok stok) {
        String urunAdi = ResultDecoder.getDataResult(satisFeignClient.findUrunAdiById(stok.getUrunId()));
        return KritikStokDurumDto.builder()
                .adet(stok.getAdet())
                .urunId(stok.getUrunId())
                .urunAdi(urunAdi)
                .kritikMi(stok.getAdet() < KRITIK_STOK_LIMIT)
                .build();
    }
}
