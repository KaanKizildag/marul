package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
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

@RequestMapping("v1/musteri")
@RestController
@RequiredArgsConstructor
public class MusteriController {

    private final MusteriService musteriService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<MusteriDto> musteriDtoList = musteriService.findAll();
        return new SuccessDataResult<>(musteriDtoList, String.format("%d tane müşteri listelendi.", musteriDtoList.size()));
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("musteriId") Long musteriId) {
        MusteriDto musteriDto = musteriService.findById(musteriId);
        return new SuccessDataResult<>(musteriDto, "müşteri başariyla bulundu");
    }

    @GetMapping("/existsById")
    public Result existsById(@RequestParam("musteriId") Long musteriId) {
        boolean musteriBulunduMu = musteriService.existsById(musteriId);
        return new SuccessDataResult<>(musteriBulunduMu, "müşteri sorgulandı");
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid MusteriDto musteriDto) {
        musteriDto = musteriService.save(musteriDto);
        return new SuccessDataResult<>(musteriDto, "Müşteri başarıyla kaydedildi.");
    }

    @GetMapping("/rapor/findAll")
    public Result raporFindAll() {
        return new SuccessDataResult<>(musteriService.musteriRaporla(), "rapor başarıyla oluşturulddu");
    }

    @GetMapping("/rapor/findAll-dev")
    public ResponseEntity<byte[]> raporFindAllDev() {
        byte[] report = musteriService.musteriRaporla();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(report, headers, HttpStatus.OK);
    }
}
