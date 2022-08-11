package com.marul.rapor;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
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
    public Result generateSimpleReport(@RequestBody List<RaporKriterleriDto> raporKriterleriDtoList) throws IOException {
        log.info("generateSimpleReport");
        ByteArrayResource byteArrayResource = raporService.generateSimpleReport(raporKriterleriDtoList);
        log.info("rapor basariyla oluşturuldu");
        return new SuccessDataResult<>(byteArrayResource.getByteArray());
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
