package com.marul.satis;

import com.marul.dto.MusteriDto;
import com.marul.dto.SatisDto;
import com.marul.dto.musteri.MusteriResponseDto;
import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
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

    public List<SatisDto> findAll() {
        List<Satis> satisList = satisRepository.findAll();
        return satisList.stream().map(this::getSatisDto)
                .collect(Collectors.toList());
    }

    private SatisDto getSatisDto(Satis satis) {
        Long musteriId = satis.getMusteriId();
        MusteriDto musteriDto = ResultDecoder.getDataResult(musteriFeignClient.findById(musteriId.intValue()));
        SatisDto satisDto = new SatisDto();
        satisDto.setMusteriDto(musteriDto);
        return satisDto;
    }

    public SatisDto save(SatisDto satisDto) {
        Satis satis = satisMapper.getEntity(satisDto);
        ResultDecoder.getDataResult(musteriFeignClient.findById(satis.getMusteriId().intValue()));
        satis = this.satisRepository.save(satis);
        return satisMapper.getDto(satis);
    }
}
