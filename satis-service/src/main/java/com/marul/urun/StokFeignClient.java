package com.marul.urun;

import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.stok.StokDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "stok-service")
public interface StokFeignClient {
    @PostMapping("/stok-service/v1/stok/save")
    SuccessDataResult<StokDto> save(@RequestBody StokDto stokDto);

    @PutMapping("/stok-service/v1/stok/stok-guncelle")
    SuccessDataResult<Boolean> stokGuncelle(@RequestParam(value = "urunId") Long urunId,
                                            @RequestParam(value = "stok") Long stok);
}
