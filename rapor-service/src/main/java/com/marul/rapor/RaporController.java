package com.marul.rapor;

import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
@Slf4j
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
    public Result generateSimpleReport(@RequestBody RaporOlusturmaDto raporOlusturmaDto) throws IOException {
        log.info("generateSimpleReport");
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto);
        log.info("rapor basariyla oluşturuldu");
        return new SuccessDataResult<>(report, "rapor başarıyla oluşturuldu");
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
