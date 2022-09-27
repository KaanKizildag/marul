package com.marul.stokservice.stokhareketi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StokHarektiService {

    private final StokHareketiRepository stokHareketiRepository;
    private final StokHareketiMapper stokHareketiMapper;
    private final Clock clock;

    public StokHareketiDto save(StokHareketiDto stokHareketiDto) {
        StokHareketi stokHareketi = stokHareketiMapper.getEntity(stokHareketiDto);
        stokHareketi.setHareketZamani(LocalDateTime.now(clock));
        stokHareketi = stokHareketiRepository.save(stokHareketi);
        return stokHareketiMapper.getDto(stokHareketi);
    }

}
