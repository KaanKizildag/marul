package com.marul.stokservice.stok;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.stok.StokDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/stok")
@RequiredArgsConstructor
@Slf4j
public class StokController {

    private final StokService stokService;

    @PostMapping("/save")
    public Result save(@RequestBody @Valid StokDto stokDto) {
        stokDto = stokService.save(stokDto);
        log.info("{} stok başarıyla kaydedildi.", stokDto);
        return new SuccessDataResult<>(stokDto);
    }

    @GetMapping("/yeterli-stok-var-mi")
    @Deprecated(forRemoval = true)
    /**
     * @deprecated iş kuralları stok servisi içinde yapılacağı için dışarı açılması gereksiz.
     */
    public Result yeterliStokVarMi(@RequestParam(value = "urunId") Long urunId,
                                   @RequestParam(value = "stok") Long stok) {
        boolean stokVarMi = stokService.yeterliStokVarMi(urunId, stok);
        log.info("stok sorgulandı");
        return new SuccessDataResult<>(stokVarMi, "stok başarıyla sorgulandı.");
    }

    @PutMapping("/stok-guncelle")
    public Result stokGuncelle(@RequestParam(value = "urunId") Long urunId,
                               @RequestParam(value = "stok") Long stok) {
        boolean stokGuncellendiMi = stokService.stokGuncelle(urunId, stok);
        log.info("stok guncelleme durumu: {}", stokGuncellendiMi ? "başarılı" : "başarısız");
        return new SuccessDataResult<>(stokGuncellendiMi, stokGuncellendiMi ? "başarılı" : "başarısız");
    }


}
