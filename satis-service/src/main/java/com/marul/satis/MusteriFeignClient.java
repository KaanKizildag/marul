package com.marul.satis;

import com.marul.dto.result.SuccessDataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "musteri-service")
public interface MusteriFeignClient {

    @GetMapping("/musteri-service/v1/musteri/existsById/{musteriId}")
    SuccessDataResult<Boolean> existsById(@PathVariable("musteriId") Long musteriId);
}

