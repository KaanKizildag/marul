package com.marul.rapor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
    @SneakyThrows
    public ResponseEntity<ByteArrayResource> generateSimpleReport(@RequestBody List<RaporKriterleriDto> raporKriterleriDtoList) {
        ByteArrayResource byteArrayResource = raporService.generateSimpleReport(raporKriterleriDtoList);
        return ResponseEntity.ok(byteArrayResource);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
