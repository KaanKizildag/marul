package com.marul.musteri;

import com.marul.dto.RaporKriterleriDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("rapor-service")
public interface RaporServiceFeignClient {
    @PostMapping("/generateSimpleReport")
    ResponseEntity<ByteArrayResource> generateSimpleReport(List<RaporKriterleriDto> raporKriterleriDtoList);
}
