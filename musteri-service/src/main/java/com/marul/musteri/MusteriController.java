package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        musteriService.deleteById(id);
        return new SuccessDataResult<>( "Müşteri başarıyla silindi.");
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid MusteriDto musteriDto) {
        musteriDto = musteriService.update(musteriDto);
        return new SuccessDataResult<>(musteriDto, "Müşteri başarıyla güncellendi.");
    }

}
