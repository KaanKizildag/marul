package com.marul.integration;

import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.result.SuccessResult;
import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "stok-service")
interface StokFeignClient {

    @PostMapping("/stok-service/v1/stok/save")
    SuccessDataResult<StokDto> save(@RequestBody StokKaydetDto stokKaydetDto);

    @PutMapping("/stok-service/v1/stok/stok-guncelle")
    SuccessDataResult<Boolean> stokGuncelle(@RequestParam(value = "urunId") Long urunId,
                                            @RequestParam(value = "stok") Long stok);

    @DeleteMapping("/stok-service/v1/stok/stok-sil")
    SuccessResult stokSil(@RequestParam("urunId") Long urunId);
}
