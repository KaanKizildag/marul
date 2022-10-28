package com.marul.kasahareketi;

import com.marul.dto.satis.KasaHareketiDto;
import com.marul.dto.satis.KasaHareketiInsertDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KasaHareketiService {

    private final KasaHareketiRepository kasaHareketiRepository;
    private final KasaHareketiMapper kasaHareketiMapper;

    public KasaHareketiDto save(KasaHareketiInsertDto kasaHareketiInsertDto) {
        KasaHareketi kasaHareketi = kasaHareketiMapper.getEntity(kasaHareketiInsertDto);
        kasaHareketi = kasaHareketiRepository.save(kasaHareketi);
        log.info("{} tutarinda kasa hareketi kaydedildi", kasaHareketiInsertDto.getTutar());
        return kasaHareketiMapper.getDto(kasaHareketi);
    }

    public Long toplamKasaTutari() {
        Long kasaTutarToplam = kasaHareketiRepository.kasaTutarToplam();
        log.info("kasa toplam tutarı sorgulandı {}", kasaTutarToplam);
        return kasaTutarToplam;
    }
}
