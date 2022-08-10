package com.marul.tur;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tur")
@RequiredArgsConstructor
public class TurController {
    private final TurService turService;

    @GetMapping("/findAll")
    public ResponseEntity<Result> findAll() {
        List<TurDto> turList = turService.findAll();
        Result result = new DataResult<>(turList);
        result.setSuccess(true);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Result> findById(@PathVariable("id") Long id) {
        TurDto turDto = turService.findById(id);
        Result result = new DataResult<>(turDto);
        result.setSuccess(true);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody TurDto turDto) {
        turService.save(turDto);
        Result result = new Result(true, "Tur basariyla kaydedildi");
        return ResponseEntity.ok(result);
    }

}
