package com.marul.satis;

import com.marul.dto.SatisDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Result save(@RequestBody SatisDto satisDto) {
        SatisDto satisDtoResult = satisService.save(satisDto);
        return new SuccessDataResult<>(satisDtoResult, "satis basariyla kaydedildi.");
    }
}
