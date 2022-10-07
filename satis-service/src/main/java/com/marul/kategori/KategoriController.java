package com.marul.kategori;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/kategori")
@RequiredArgsConstructor
@Slf4j
public class KategoriController {

    private final KategoriService kategoriService;

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Long id) {
        KategoriDto kategoriDto = kategoriService.findById(id);
        log.info("{} id ile kategori başarıyla bulundu.", id);
        return new SuccessDataResult<>(kategoriDto, "kategori başarıyla bulundu");
    }

    @PostMapping("/save")
    public Result findById(@RequestBody KategoriDto kategoriDto) {
        kategoriDto = kategoriService.save(kategoriDto);
        log.info("kategori başarıyla kaydedildi. kategori id: {}", kategoriDto.getId());
        return new SuccessDataResult<>(kategoriDto, "kategori başarıyla kaydedildi");
    }

}
