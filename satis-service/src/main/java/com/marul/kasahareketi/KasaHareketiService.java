package com.marul.kasahareketi;

import com.marul.dto.satis.KasaHareketiDto;
import com.marul.dto.satis.KasaHareketiInsertDto;
import com.marul.satis.dto.SatisInsertDto;
import com.marul.urun.UrunService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.marul.kafka.MarulTopicNames.MARUL_SATIS;

@Service
@AllArgsConstructor
@Slf4j
public class KasaHareketiService {

    private final KasaHareketiRepository kasaHareketiRepository;
    private final KasaHareketiMapper kasaHareketiMapper;
    private final UrunService urunService;

    public KasaHareketiDto save(KasaHareketiInsertDto kasaHareketiInsertDto) {
        KasaHareketi kasaHareketi = kasaHareketiMapper.getEntity(kasaHareketiInsertDto);
        kasaHareketi = kasaHareketiRepository.save(kasaHareketi);
        log.info("{} tutarinda kasa hareketi kaydedildi", kasaHareketiInsertDto.getTutar());
        return kasaHareketiMapper.getDto(kasaHareketi);
    }

    @KafkaListener(topics = MARUL_SATIS, groupId = "group-id")
    private void kasaHareketiOlustur(SatisInsertDto satisInsertDto) {
        satisInsertDto.getSatisDtoList()
                .forEach(satisDto -> {
                    Long urunId = satisDto.getUrunId();
                    BigDecimal satislanAdet = BigDecimal.valueOf(satisDto.getSatilanAdet());
                    BigDecimal fiyat = urunService.findById(urunId).getFiyat();

                    KasaHareketiInsertDto kasaHareketiInsertDto = new KasaHareketiInsertDto();
                    kasaHareketiInsertDto.setAciklama("ürün satışı");
                    kasaHareketiInsertDto.setTutar(fiyat.multiply(satislanAdet));

                    save(kasaHareketiInsertDto);
                });
    }

    public Long toplamKasaTutari() {
        Long kasaTutarToplam = kasaHareketiRepository.kasaTutarToplam()
                .orElse(0L);
        log.info("kasa toplam tutarı sorgulandı {}", kasaTutarToplam);
        return kasaTutarToplam;
    }
}
