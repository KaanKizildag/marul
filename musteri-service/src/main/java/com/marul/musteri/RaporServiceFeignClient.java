package com.marul.musteri;

import com.marul.dto.RaporKriterleriDto;
import com.marul.dto.result.SuccessDataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "rapor-service")
public interface RaporServiceFeignClient {
    // todo api gateway eklendiginde api gateway uzerinden servise erisecek!
    @PostMapping("/v1/rapor/generateSimpleReport")
    SuccessDataResult<byte[]> generateSimpleReport(List<RaporKriterleriDto> raporKriterleriDtoList);
}
