package com.marul.urun;

import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.urun.UrunDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/urun")
@RequiredArgsConstructor
@Slf4j
public class UrunController {

    private final UrunService urunService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<UrunDto> urunDtoList = urunService.findAll();
        log.info("{} tane ürün listelendi", urunDtoList.size());
        return new SuccessDataResult<>(urunDtoList,
                String.format("%d tane ürün listelendi", urunDtoList.size()));
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam(value = "id") Long id) {
        UrunDto urunDto = urunService.findById(id);
        log.info("{} id ile ürün başarıyla bulundu", id);
        return new SuccessDataResult<>(urunDto, "ürün başarıyla getirildi.");
    }

    @GetMapping("/existsById")
    public Result existsById(@RequestParam(value = "id") Long id) {
        boolean exists = urunService.existsById(id);
        log.info("{} id ile ürün başarıyla bulundu", id);
        return new SuccessDataResult<>(exists, "ürün bilgisi başarıyla getirildi.");
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid UrunDto urunDto) {
        urunDto = urunService.save(urunDto);
        return new SuccessDataResult<>(urunDto, "ürün başarıyla kaydedildi.");
    }
}
