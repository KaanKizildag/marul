package com.marul.stokservice.integration;

import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.urun.UrunDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "satis-service", path = "/satis-service/v1")
interface SatisFeignClient {
    @GetMapping("/urun/existsById")
    SuccessDataResult<Boolean> existsUrunById(@RequestParam(value = "id") Long id);

    @GetMapping("/urun/findById")
    SuccessDataResult<UrunDto> findUrunById(@RequestParam("id") Long id);
}
