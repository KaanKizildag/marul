/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
import com.marul.exception.BulunamadiException;
import com.marul.exception.EmailDahaOnceAlinmisException;
import com.marul.tur.TurDto;
import com.marul.tur.TurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        musteriDtoList = musteriDtoList.stream()
                .map(musteriDto -> {
                    Long turId = musteriDto.getTurId();
                    TurDto turDto = turService.findById(turId);
                    musteriDto.setTurAdi(turDto.getTurAdi());
                    return musteriDto;
                })
                .collect(Collectors.toList());
        return musteriDtoList;
    }

    public MusteriDto findById(Long id) {
        Musteri musteri = musteriRepository.findById(id)
                .orElseThrow(() -> new BulunamadiException("%s ile bir müşteri bulunamadı", id.toString()));
        return musteriMapper.getTarget(musteri);
    }

    public boolean existsById(Long musteriId) {
        return musteriRepository.existsById(musteriId);
    }

    public void deleteById(Long id) {
        Musteri deletedMusteri = musteriRepository.findById(id)
                .orElseThrow(() -> new BulunamadiException("Müsteri bulunamadi id:%s ", id));
        musteriRepository.delete(deletedMusteri);
    }
//
//    public void update(Long musteriId, MusteriDto musteriDto) {
//        MusteriDto byId = findById(musteriId);
//
//        byId.setMusteriAdi(musteriDto.getMusteriAdi());
//        byId.setBorc(musteriDto.getBorc());
//        byId.setTelefonNo(musteriDto.getTelefonNo());
//        byId.setTurId(musteriDto.getTurId());
//        byId.setTeslimatNoktasi(musteriDto.getTeslimatNoktasi());
//        byId.setEmail(musteriDto.getEmail());
//
//        Musteri musteri = musteriMapper.getSource(byId);
//        musteriRepository.save(musteri);
//    }

    public MusteriDto save(MusteriDto musteriDto) {
        emailAlinmisMiKontrol(musteriDto.getEmail());
        turVarMi(musteriDto.getTurId());
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteri = musteriRepository.save(musteri);
        return musteriMapper.getTarget(musteri);
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
            throw new BulunamadiException("%s id ile tur bulunamadı.", String.valueOf(turId));
        }
    }

    private void emailAlinmisMiKontrol(String email) {
        if (musteriRepository.existsByEmail(email)) {
            throw new EmailDahaOnceAlinmisException("%s bu email daha önce alınmış.", email);
        }
    }
}
