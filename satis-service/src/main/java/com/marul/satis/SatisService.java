package com.marul.satis;

import com.marul.dto.SatisDto;
import com.marul.urun.UrunService;
import com.marul.util.ResultDecoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SatisService {

    private final SatisRepository satisRepository;
    private final MusteriFeignClient musteriFeignClient;
    private final SatisMapper satisMapper;
    private final UrunService urunService;

    public List<SatisDto> findAll() {
        List<Satis> satisList = satisRepository.findAll();
        return satisList.stream().map(satisMapper::getDto)
                .collect(Collectors.toList());
    }

    public SatisDto save(SatisDto satisDto) {
        ResultDecoder.getDataResult(musteriFeignClient.findById(satisDto.getMusteriId()));
        urunService.findById(satisDto.getUrunId());
        Satis satis = satisMapper.getEntity(satisDto);
        satis = this.satisRepository.save(satis);
        return satisMapper.getDto(satis);
    }
}
