package com.marul.stokservice.stokhareketi;

import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.stok.StokDto;
import com.marul.dto.urun.UrunDto;
import com.marul.stokservice.integration.RaporServiceIntegration;
import com.marul.stokservice.integration.SatisServiceIntegration;
import com.marul.stokservice.stok.StokService;
import com.marul.stokservice.stokhareketi.dto.StokHareketRaporDto;
import com.marul.stokservice.stokhareketi.dto.StokHareketiDto;
import com.marul.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
    private final RaporServiceIntegration raporServiceIntegration;
    private final SatisServiceIntegration satisServiceIntegration;
    private final StokService stokService;

    public StokHarektiService(StokHareketiRepository stokHareketiRepository,
                              StokHareketiMapper stokHareketiMapper,
                              Clock clock,
                              RaporServiceIntegration raporServiceIntegration,
                              SatisServiceIntegration satisServiceIntegration,
                              @Lazy StokService stokService) {
        this.stokHareketiRepository = stokHareketiRepository;
        this.stokHareketiMapper = stokHareketiMapper;
        this.clock = clock;
        this.raporServiceIntegration = raporServiceIntegration;
        this.satisServiceIntegration = satisServiceIntegration;
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
        List<StokHareketi> stokHareketiList = stokHareketiRepository.findAllOrderByIdDesc();
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
        return raporServiceIntegration.generateSimpleReport(raporOlusturmaDto);
    }

    private List<StokHareketRaporDto> getHareketRaporDtos(List<StokHareketiDto> stokHareketiDtoList) {
        return stokHareketiDtoList.stream()
                .map(this::createStokHareketRaporDto)
                .collect(Collectors.toList());
    }

    private StokHareketRaporDto createStokHareketRaporDto(StokHareketiDto stokHareketiDto) {
        Long stokId = stokHareketiDto.getStokId();
        StokDto stokDto = stokService.findById(stokId);
        UrunDto urunDto = satisServiceIntegration.findUrunById(stokDto.getUrunId());
        return StokHareketRaporDto.builder()
                .urunAdi(urunDto.getUrunAdi())
                .miktar(stokHareketiDto.getMiktar())
                .aciklama(stokHareketiDto.getAciklama())
                .hareketZamani(DateUtil.dateFromLocalDateTime(stokHareketiDto.getHareketZamani()))
                .build();
    }

    private RaporOlusturmaDto getRaporOlusturmaDto(List<StokHareketRaporDto> stokHareketiDtoList) {
        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto();
        raporOlusturmaDto.setRaporAdi("stok-hareket.jrxml");
        List<RaporDto> raporDto = stokHareketiMapper.getRaporDto(stokHareketiDtoList);
        raporOlusturmaDto.setRaporDtoList(raporDto);
        HashMap<String, Object> raporParametreleri = new HashMap<>();
        raporParametreleri.put("baslangicTarihi", null);
//        raporParametreleri.put("baslangicTarihi", DateUtil.dateFromLocalDateTime(LocalDateTime.now()));
        raporParametreleri.put("bitisTarihi", null);
//        raporParametreleri.put("bitisTarihi", DateUtil.dateFromLocalDateTime(LocalDateTime.now()));
        raporOlusturmaDto.setRaporParametreleri(raporParametreleri);
        return raporOlusturmaDto;
    }
}
