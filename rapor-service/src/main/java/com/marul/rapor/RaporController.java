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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.marul.rapor.ExportReportType.EXCEL;
import static com.marul.rapor.ExportReportType.PDF;

@RestController
@RequestMapping("/v1/rapor")
@RequiredArgsConstructor
@Slf4j
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/generateSimpleReport")
    public Result generateSimpleReport(@RequestBody @Valid RaporOlusturmaDto raporOlusturmaDto) {
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto, PDF);
        log.info("rapor basariyla oluşturuldu rapor boyutu: {} Byte", report.length);
        return new SuccessDataResult<>(report, "rapor başarıyla oluşturuldu");
    }

    @PostMapping(value = "/generateSimpleReport-excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateSimpleReportExcel(@RequestBody @Valid RaporOlusturmaDto raporOlusturmaDto) {
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto, EXCEL);
        return ResponseEntity.ok()
                .header("Content-Disposition", "form-data; filename=\"output.xlsx\"")
                .body(report);
    }

    @PostMapping("/generateSimpleReport-pdf")
    public ResponseEntity<byte[]> generateSimpleReportPdf(@RequestBody @Valid RaporOlusturmaDto raporOlusturmaDto) {
        byte[] report = raporService.generateSimpleReport(raporOlusturmaDto, PDF);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }
}
