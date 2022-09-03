package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/musteri")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MusteriController {

    private final MusteriService musteriService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<MusteriDto> musteriDtoList = musteriService.findAll();
        return new SuccessDataResult<>(musteriDtoList, String.format("%d tane müşteri listelendi.", musteriDtoList.size()));
    }

    @GetMapping("/findById/{musteriId}")
    public Result findById(@PathVariable("musteriId") Long musteriId) {
        MusteriDto musteriDto = musteriService.findById(musteriId);
        return new SuccessDataResult<>(musteriDto, "müşteri başariyla bulundu");
    }

    @PostMapping("/save")
    public Result save(/*@Valid*/ @RequestBody MusteriDto musteriDto) {
        musteriDto = musteriService.save(musteriDto);
        return new SuccessDataResult<>(musteriDto, "Müşteri başarıyla kaydedildi.");
    }

    @GetMapping("/rapor/findAll")
    public Result raporFindAll() {
        return new SuccessDataResult<>(musteriService.musteriRaporla(), "rapor başarıyla oluşturulddu");
    }
}
