package com.marul.stokservice.stokhareketi;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/stok-hareket")
@RestController
@RequiredArgsConstructor
public class StokHareketController {

    private final StokHarektiService stokHarektiService;

    @GetMapping("/stok-hareket-raporu")
    public Result getStokHareketRapor() {
        byte[] rapor = stokHarektiService.raporOlustur();
        return new SuccessDataResult<>(rapor, "hareket raporu basariyla oluşturuldu");
    }

    @GetMapping(value = "/stok-hareket-raporu-dev")
    public ResponseEntity<byte[]> getStokHareketRaporDev() {
        byte[] rapor = stokHarektiService.raporOlustur();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(rapor, headers, HttpStatus.OK);
    }
}
