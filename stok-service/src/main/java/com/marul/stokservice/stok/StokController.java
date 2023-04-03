package com.marul.stokservice.stok;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.result.SuccessResult;
import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.stokservice.stok.dto.KritikStokDurumDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stok")
@RequiredArgsConstructor
@Slf4j
public class StokController {

    private final StokService stokService;

    @PostMapping("/save")
    public Result save(@RequestBody @Valid StokKaydetDto stokKaydetDto) {
        StokDto stokDto = stokService.save(stokKaydetDto);
        log.info("{} stok başarıyla kaydedildi.", stokDto);
        return new SuccessDataResult<>(stokDto);
    }

    @PutMapping("/stok-guncelle")
    public Result stokGuncelle(@RequestParam(value = "urunId") Long urunId,
                               @RequestParam(value = "stok") Long stok) {
        boolean stokGuncellendiMi = stokService.stokGuncelle(urunId, stok);
        log.info("stok guncelleme durumu: {}", stokGuncellendiMi ? "başarılı" : "başarısız");
        return new SuccessDataResult<>(stokGuncellendiMi, stokGuncellendiMi ? "başarılı" : "başarısız");
    }

    @GetMapping("/kritik-stoklari-getir")
    public Result kritikStoklariGetir() {
        List<KritikStokDurumDto> kritikStokDurumDtoList = stokService.findAllByOrOrderByAdetAsc();
        String mesaj = "kritik stoklar listelendi";
        log.info(mesaj);
        return new SuccessDataResult<>(kritikStokDurumDtoList, mesaj);
    }

    @DeleteMapping("/stok-sil")
    public Result stokSil(@RequestParam("urunId") Long urunId) {
        stokService.urunIdIleStokSil(urunId);
        return new SuccessResult("stok başarıyla silindi");
    }
}
