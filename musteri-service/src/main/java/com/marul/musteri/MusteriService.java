/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
import com.marul.exception.AlreadyExistsException;
import com.marul.exception.NotFoundException;
import com.marul.tur.TurDto;
import com.marul.tur.TurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MusteriService {

    private final MusteriRepository musteriRepository;
    private final TurService turService;
    private final MusteriMapper musteriMapper;

    public List<MusteriDto> findAll() {
        List<Musteri> musteriList = musteriRepository.findAll();
        List<MusteriDto> musteriDtoList = musteriMapper.getTargetList(musteriList);
        for (MusteriDto musteriDto : musteriDtoList) {
            Long turId = musteriDto.getTurId();
            TurDto turDto = turService.findById(turId);
            musteriDto.setTurAdi(turDto.getTurAdi());
        }
        return musteriDtoList;
    }


    public MusteriDto findById(Long id) {
        Musteri musteri = musteriRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("%s ile bir müşteri bulunamadı", id.toString()));
        return musteriMapper.getTarget(musteri);
    }

    public boolean existsById(Long musteriId) {
        return musteriRepository.existsById(musteriId);
    }

    public void deleteById(Long id) {
        Musteri deletedMusteri = musteriRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Müsteri bulunamadi id:%s ", id));
        musteriRepository.delete(deletedMusteri);
    }

    public MusteriDto save(MusteriDto musteriDto) {
        validateMusteriDto(musteriDto);
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteri = musteriRepository.save(musteri);
        return musteriMapper.getTarget(musteri);
    }

    private void validateMusteriDto(MusteriDto musteriDto) {
        emailAlinmisMiKontrol(musteriDto.getEmail());
        turVarMi(musteriDto.getTurId());
    }

    public MusteriDto update(MusteriDto musteriDto) {
        turVarMi(musteriDto.getTurId());
        findById(musteriDto.getId());
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteriRepository.save(musteri);
        return musteriDto;
    }

    private void turVarMi(long turId) {
        if (!turService.existsByTurId(turId)) {
            throw new NotFoundException("%s id ile tur bulunamadı.", String.valueOf(turId));
        }
    }

    private void emailAlinmisMiKontrol(String email) {
        if (musteriRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("%s bu email daha önce alınmış.", email);
        }
    }
}
