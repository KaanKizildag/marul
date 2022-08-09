package com.marul.musteri;

import com.marul.dto.RaporKriterleriDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "rapor-service")
public interface RaporServiceFeignClient {
    // todo api gateway eklendiginde api gateway uzerinden servise erisecek!
    @PostMapping("/v1/rapor/generateSimpleReport")
    ResponseEntity<ByteArrayResource> generateSimpleReport(List<RaporKriterleriDto> raporKriterleriDtoList);
}
