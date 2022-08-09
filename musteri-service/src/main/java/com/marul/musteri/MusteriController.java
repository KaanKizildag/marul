package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.dto.MusteriEklemeResponse;
import com.marul.dto.RaporKriterleriDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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
    public ResponseEntity<List<MusteriDto>> findAll() {
        return ResponseEntity.ok().body(musteriService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<MusteriEklemeResponse> save(/*@Valid*/ @RequestBody MusteriDto musteriDto) {
        musteriService.save(musteriDto);
        MusteriEklemeResponse musteriEklemeResponse = new MusteriEklemeResponse();
        musteriEklemeResponse.setCevapMesaji("Müşteri başarıyla kaydedildi.");
        return ResponseEntity.ok(musteriEklemeResponse);
    }

    //    @SneakyThrows
    @GetMapping("/rapor/findAll")
    public ResponseEntity<ByteArrayResource> raporFindAll() {
        List<MusteriDto> musteriDtoList = musteriService.findAll();
        List<RaporKriterleriDto> raporKriterleriDTOList = musteriDtoList.stream()
                .map(musteriDto -> RaporKriterleriDto.builder()
                        .email(musteriDto.getEmail())
                        .musteriAdi(musteriDto.getMusteriAdi())
                        .build())
                .collect(Collectors.toList());

        ByteArrayResource raporByteArray = musteriService.generateSimpleReport(raporKriterleriDTOList);

        return ResponseEntity.ok(raporByteArray);
    }
}
