package com.marul.stokservice.stok;

import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.urun.UrunDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "satis-service")
public interface SatisFeignClient {
    @GetMapping("/satis-service/v1/urun/existsById")
    SuccessDataResult<Boolean> existsUrunById(@RequestParam(value = "id") Long id);

    @GetMapping("/satis-service/v1/urun/findUrunAdiById")
    SuccessDataResult<String> findUrunAdiById(@RequestParam("id") Long id);

    @GetMapping("/satis-service/v1/urun/findById")
    SuccessDataResult<UrunDto> findById(@RequestParam("id") Long id);
}
