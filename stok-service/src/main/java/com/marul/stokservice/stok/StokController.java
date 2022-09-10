package com.marul.stokservice.stok;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stok")
@RequiredArgsConstructor
@Slf4j
public class StokController {

    private final StokService stokService;

    @PostMapping("/save")
    public Result save(@RequestBody StokDto stokDto) {
        stokDto = stokService.save(stokDto);
        log.info("{} stok başarıyla kaydedildi.", stokDto);
        return new SuccessDataResult<>(stokDto);
    }

    @GetMapping("/yeterli-stok-var-mi")
    public Result yeterliStokVarMi(@RequestParam(value = "urunId") Long urunId,
                                   @RequestParam(value = "stok") Long stok) {
        boolean stokVarMi = stokService.yeterliStokVarMi(urunId, stok);
        log.info("stok sorgulandı");
        return new SuccessDataResult<>(stokVarMi, "stok başarıyla sorgulandı.");
    }


}