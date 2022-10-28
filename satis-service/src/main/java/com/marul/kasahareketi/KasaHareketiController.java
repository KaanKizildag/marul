package com.marul.kasahareketi;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.satis.KasaHareketiDto;
import com.marul.dto.satis.KasaHareketiInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/kasa-hareketi")
@Validated
public class KasaHareketiController {
    private final KasaHareketiService kasaHareketiService;

    @GetMapping("/kasa-toplam-tutari")
    public Result kasaToplamTutari() {
        Long toplamKasaTutari = kasaHareketiService.toplamKasaTutari();
        return new SuccessDataResult<>(toplamKasaTutari, "kasa tutarı sorgulandı");
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid KasaHareketiInsertDto kasaHareketiInsertDto) {
        KasaHareketiDto kasaHareketiDto = kasaHareketiService.save(kasaHareketiInsertDto);
        return new SuccessDataResult<>(kasaHareketiDto, "kasa hareketi kaydedildi");
    }

}
