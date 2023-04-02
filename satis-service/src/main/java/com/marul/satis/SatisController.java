package com.marul.satis;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.satis.SatisResponseDto;
import com.marul.satis.dto.SatisInsertDto;
import com.marul.satis.dto.SonSatisOzetiDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/satis")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SatisController {

    private final SatisService satisService;
    private final SatisAnalizService satisAnalizService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<SatisResponseDto> satisList = satisService.findAll();
        return new SuccessDataResult<>(satisList,
                String.format("%d tane satış başarıyla listelendi", satisList.size()));
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid SatisInsertDto satisInsertDto) {
        List<SatisResponseDto> satisResponseDtoList = satisService.save(satisInsertDto);
        return new SuccessDataResult<>(satisResponseDtoList,
                satisResponseDtoList.size() + " tane satış başarıyla kaydedildi.");
    }

    @GetMapping("/find-urun-adi-by-satisId")
    public Result findUrunAdiBySatisId(@RequestParam("satisId") Long satisId) {
        String urunAdi = satisService.findUrunAdiBySatisId(satisId);
        return new SuccessDataResult<>(urunAdi, "ürün adı başarıyla getirildi");
    }

    @GetMapping("/onceki-haftaya-gore-satis-dustu-mu")
    public Result haftalikSatislariGetir() {
        Map<String, BigDecimal> kategoriSatisAnalizDtoList = satisAnalizService.haftalikSatislariGetir();
        String message = "haftalık satışlar listelendi";
        log.info(message);
        return new SuccessDataResult<>(kategoriSatisAnalizDtoList, message);
    }

    @GetMapping("/satis-faturasi")
    public Result satisFaturasiGetir(@RequestParam("musteriId") Long musteriId) {
        byte[] satisRaporu = satisService.generateSalesReport(musteriId);
        return new SuccessDataResult<>(satisRaporu, "satis raporu oluşturuldu");
    }

    @GetMapping(value = "/satis-faturasi-dev", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> satisFaturasiGetirDev(@RequestParam("musteriId") Long musteriId) {
        byte[] satisRaporu = satisService.generateSalesReport(musteriId);

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf");
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return ResponseEntity.ok()
                .headers(headers)
                .body(satisRaporu);
    }

    @GetMapping("/son-satislari-getir")
    public Result sonSatislariGetir() {
        List<SonSatisOzetiDto> sonSatisOzetiDtos = satisService.sonSatislariGetir();
        return new SuccessDataResult<>(sonSatisOzetiDtos, "satışlar listelendi");
    }
}
