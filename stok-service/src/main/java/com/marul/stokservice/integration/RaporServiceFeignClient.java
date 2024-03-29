package com.marul.stokservice.integration;

import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.result.SuccessDataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "rapor-service")
interface RaporServiceFeignClient {
    @PostMapping("/rapor-service/v1/rapor/generateSimpleReport")
    SuccessDataResult<byte[]> generateSimpleReport(RaporOlusturmaDto raporOlusturmaDto);
}
