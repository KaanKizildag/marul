/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.dto.RaporKriterleriDto;
import com.marul.dto.result.DataResult;
import com.marul.exception.EmailDahaOnceAlinmisException;
import com.marul.tur.TurService;
import com.marul.util.ResultDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author kaan
 */
@Service
@RequiredArgsConstructor
public class MusteriService {

    private final MusteriRepository musteriRepository;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final TurService turService;
    private final MusteriMapper musteriMapper;

    public void insert(MusteriDto musteriDto) {
        musteriRepository.save(musteriMapper.getSource(musteriDto));
    }

    public List<MusteriDto> findAll() {
        List<Musteri> musteriList = musteriRepository.findAll();
        return musteriMapper.getTargetList(musteriList);
    }

    public Optional<MusteriDto> findById(Integer id) {
        Musteri musteri = musteriRepository.getById(id);
        MusteriDto musteriDto = musteriMapper.getTarget(musteri);
        return Optional.of(musteriDto);
    }

    public void delete(Integer id) {
        musteriRepository.deleteById(id);
    }

    public void update(MusteriDto musteriDto) {
        musteriRepository.save(musteriMapper.getSource(musteriDto));
    }

    public MusteriDto save(MusteriDto musteriDto) {
        if (musteriRepository.existsByEmail(musteriDto.getEmail())) {
            throw new EmailDahaOnceAlinmisException("%s bu email daha önce alınmış.", musteriDto.getEmail());
        }
        turService.findById(musteriDto.getTurId());
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteri = musteriRepository.save(musteri);
        return musteriMapper.getTarget(musteri);
    }

    public byte[] generateSimpleReport(List<RaporKriterleriDto> raporKriterleriDTOList) {
        DataResult<byte[]> dataResult = raporServiceFeignClient
                .generateSimpleReport(raporKriterleriDTOList);
        return ResultDecoder.getDataResult(dataResult);
    }
}
