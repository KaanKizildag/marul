package com.marul.kategori;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findAll")
    public Result findById() {
        List<KategoriDto> kategoriDtoList = kategoriService.findAll();
        String message = String.format("%d tane kategori başarıyla listelendi", kategoriDtoList.size());
        log.info(message);
        return new SuccessDataResult<>(kategoriDtoList, message);
    }

    @PostMapping("/save")
    public Result findById(@RequestBody KategoriDto kategoriDto) {
        kategoriDto = kategoriService.save(kategoriDto);
        log.info("kategori başarıyla kaydedildi. kategori id: {}", kategoriDto.getId());
        return new SuccessDataResult<>(kategoriDto, "kategori başarıyla kaydedildi");
    }

}
