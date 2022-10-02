package com.marul.stokservice.stokhareketi;

import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.stokservice.stok.SatisFeignClient;
import com.marul.stokservice.stok.StokService;
import com.marul.util.ResultDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StokHarektiService {

    private final StokHareketiRepository stokHareketiRepository;
    private final StokHareketiMapper stokHareketiMapper;
    private final Clock clock;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final SatisFeignClient satisFeignClient;
    private final StokService stokService;

    public StokHarektiService(StokHareketiRepository stokHareketiRepository,
                              StokHareketiMapper stokHareketiMapper,
                              Clock clock,
                              RaporServiceFeignClient raporServiceFeignClient,
                              SatisFeignClient satisFeignClient,
                              @Lazy StokService stokService) {
        this.stokHareketiRepository = stokHareketiRepository;
        this.stokHareketiMapper = stokHareketiMapper;
        this.clock = clock;
        this.raporServiceFeignClient = raporServiceFeignClient;
        this.satisFeignClient = satisFeignClient;
        this.stokService = stokService;
    }


    public StokHareketiDto save(StokHareketiDto stokHareketiDto) {
        StokHareketi stokHareketi = stokHareketiMapper.getEntity(stokHareketiDto);
        stokHareketi.setHareketZamani(LocalDateTime.now(clock));
        stokHareketi = stokHareketiRepository.save(stokHareketi);
        log.info("stok hareket kaydedildi");
        return stokHareketiMapper.getDto(stokHareketi);
    }

    public List<StokHareketiDto> findAll() {
        List<StokHareketi> stokHareketiList = stokHareketiRepository.findAll();
        return stokHareketiMapper.getDtoList(stokHareketiList);
    }

    public List<StokHareketiDto> findAll(LocalDateTime baslangic, LocalDateTime bitis) {
        List<StokHareketi> stokHareketiList = stokHareketiRepository
                .findStokHareketiByHareketZamaniBetween(baslangic, bitis);
        return stokHareketiMapper.getDtoList(stokHareketiList);
    }

    public List<StokHareketiDto> findAll(LocalDateTime bitis) {
        List<StokHareketi> stokHareketiList = stokHareketiRepository
                .findStokHareketiByHareketZamaniBefore(bitis);
        return stokHareketiMapper.getDtoList(stokHareketiList);
    }

    public byte[] raporOlustur() {
        List<StokHareketiDto> stokHareketiDtoList = findAll();
        List<StokHareketRaporDto> hareketRaporDtos = getHareketRaporDtos(stokHareketiDtoList);
        RaporOlusturmaDto raporOlusturmaDto = getRaporOlusturmaDto(hareketRaporDtos);
        return ResultDecoder.getDataResult(raporServiceFeignClient.generateSimpleReport(raporOlusturmaDto));
    }

    private List<StokHareketRaporDto> getHareketRaporDtos(List<StokHareketiDto> stokHareketiDtoList) {
        return stokHareketiDtoList.stream().map(stokHareketiDto -> {
            Long stokId = stokHareketiDto.getStokId();
            Long urunId = stokService.findUrunIdByStokId(stokId);
            String urunAdi = ResultDecoder.getDataResult(satisFeignClient.findUrunAdiById(urunId));
            return StokHareketRaporDto.builder()
                    .urunAdi(urunAdi)
                    .miktar(stokHareketiDto.getMiktar())
                    .hareketZamani(stokHareketiDto.getHareketZamani())
                    .build();
        }).collect(Collectors.toList());
    }

    private RaporOlusturmaDto getRaporOlusturmaDto(List<StokHareketRaporDto> stokHareketiDtoList) {
        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto();
        raporOlusturmaDto.setRaporAdi("stok-hareket.jrxml");
        List<RaporDto> raporDto = stokHareketiMapper.getRaporDto(stokHareketiDtoList);
        raporOlusturmaDto.setRaporDtoList(raporDto);
        HashMap<String, Object> raporParametreleri = new HashMap<>();
        raporParametreleri.put("baslangicTarihi", Timestamp.valueOf(LocalDateTime.MIN));
        raporParametreleri.put("bitisTarihi", Timestamp.valueOf(LocalDateTime.now(clock)));
        raporOlusturmaDto.setRaporParametreleri(raporParametreleri);
        return raporOlusturmaDto;
    }
}
