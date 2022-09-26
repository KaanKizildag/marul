package com.marul.rapor;

import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
@Slf4j
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
    public Result generateSimpleReport(@RequestBody @Valid RaporOlusturmaDto raporOlusturmaDto) throws IOException {
        log.info("generateSimpleReport");
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto);
        log.info("rapor basariyla oluşturuldu");
        return new SuccessDataResult<>(report, "rapor başarıyla oluşturuldu");
    }

    @PostMapping("/generateSimpleReport-dev")
    public ResponseEntity<byte[]> generateSimpleReportDev(@RequestBody @Valid RaporOlusturmaDto raporOlusturmaDto) throws IOException {
        log.info("generateSimpleReport");
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto);
        log.info("rapor basariyla oluşturuldu");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "helloworld";
    }
}
