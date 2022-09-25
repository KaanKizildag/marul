package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
import com.marul.exception.BulunamadiException;
import com.marul.exception.YeterliStokYokException;
import com.marul.util.ResultDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StokService {

    private final StokRepository stokRepository;
    private final SatisFeignClient satisFeignClient;
    private final StokMapper stokMapper;

    public boolean yeterliStokVarMi(Long urunId, Long stok) {
        boolean urunVarMi = ResultDecoder.getDataResult(satisFeignClient.existsById(urunId));
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
            return stokMapper.getDto(stok);
        }
        Stok stok = stokMapper.getEntity(stokDto);
        stok = stokRepository.save(stok);
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
        return stok.getId() != null;
    }
}
