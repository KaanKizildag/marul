package com.marul.rapor;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
@Slf4j
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
//    @SneakyThrows
    public ResponseEntity<Result> generateSimpleReport(@RequestBody List<RaporKriterleriDto> raporKriterleriDtoList) throws IOException {
        log.info("generateSimpleReport");
        ByteArrayResource byteArrayResource = raporService.generateSimpleReport(raporKriterleriDtoList);
        Result result = new DataResult<>(byteArrayResource.getByteArray());
        result.setSuccess(true);
        result.setMessage("rapor başarıyla oluşturuldu");
        log.info("rapor basariyla oluşturuldu");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
