package com.marul.rapor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
//    @SneakyThrows
    public ResponseEntity<ByteArrayResource> generateSimpleReport(@RequestBody List<RaporKriterleriDto> raporKriterleriDtoList) throws IOException {
        ByteArrayResource byteArrayResource = raporService.generateSimpleReport(raporKriterleriDtoList);
        return ResponseEntity.ok(byteArrayResource);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
