package com.marul.integration;

import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.result.SuccessDataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "musteri-service")
interface MusteriFeignClient {

    @GetMapping("/musteri-service/v1/musteri/existsById")
    SuccessDataResult<Boolean> existsById(@RequestParam("musteriId") Long musteriId);

    @GetMapping("/musteri-service/v1/musteri/findById")
    SuccessDataResult<MusteriDto> findById(@RequestParam("musteriId") Long musteriId);

}

