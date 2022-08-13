package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.dto.RaporKriterleriDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1/musteri")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MusteriController {

    private final MusteriService musteriService;

    @GetMapping("/findAll")
    public ResponseEntity<Result> findAll() {
        List<MusteriDto> musteriDtoList = musteriService.findAll();
        Result result = new SuccessDataResult<>(musteriDtoList);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Result> save(/*@Valid*/ @RequestBody MusteriDto musteriDto) {
        musteriService.save(musteriDto);
        Result result = new SuccessDataResult<>(musteriDto);
        return ResponseEntity.ok(result);
    }

    //    @SneakyThrows
    @GetMapping("/rapor/findAll")
    public byte[] raporFindAll() {
        List<MusteriDto> musteriDtoList = musteriService.findAll();
        List<RaporKriterleriDto> raporKriterleriDTOList = musteriDtoList.stream()
                .map(musteriDto -> RaporKriterleriDto.builder()
                        .email(musteriDto.getEmail())
                        .musteriAdi(musteriDto.getMusteriAdi())
                        .telefonNo(musteriDto.getTelefonNo())
                        .build())
                .collect(Collectors.toList());

        byte[] simpleReport = musteriService.generateSimpleReport(raporKriterleriDTOList);
//        new Thread(() -> musteriService.mailGonder(simpleReport)).start();
        return simpleReport;
    }
}
