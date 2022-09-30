package com.marul.satis;

import com.marul.dto.SatisDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/satis")
@RequiredArgsConstructor
public class SatisController {

    private final SatisService satisService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<SatisDto> satisList = satisService.findAll();
        return new SuccessDataResult<>(satisList,
                String.format("%d tane satış başarıyla listelendi", satisList.size()));
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid SatisDto satisDto) {
        SatisDto satisDtoResult = satisService.save(satisDto);
        return new SuccessDataResult<>(satisDtoResult, "satis basariyla kaydedildi.");
    }

    @GetMapping("/find-urun-adi-by-satisId")
    public Result findUrunAdiBySatisId(@RequestParam("satisId") Long satisId) {
        String urunAdi = satisService.findUrunAdiBySatisId(satisId);
        return new SuccessDataResult(urunAdi, "ürün adı başarıyla getirildi");
    }

    @GetMapping("/satis-faturasi")
    public Result satisFaturasiGetir(@RequestParam("musteriId") Long musteriId) {
        byte[] satisRaporu = satisService.satisRaporuGetir(musteriId);
        return new SuccessDataResult<>(satisRaporu, "satis raporu oluşturuldu");
    }

    @GetMapping("/satis-faturasi-dev")
    public ResponseEntity satisFaturasiGetirDev(@RequestParam("musteriId") Long musteriId) {
        byte[] satisRaporu = satisService.satisRaporuGetir(musteriId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(satisRaporu, headers, HttpStatus.OK);

    }
}
