/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;

import com.marul.dto.MailGondermeDto;
import com.marul.dto.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.result.DataResult;
import com.marul.dto.result.SuccessResult;
import com.marul.exception.BulunamadiException;
import com.marul.exception.EmailDahaOnceAlinmisException;
import com.marul.tur.TurService;
import com.marul.util.ResultDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaan
 */
@Service
@RequiredArgsConstructor
public class MusteriService {

    private final MusteriRepository musteriRepository;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final MailSenderFeignClient mailSenderFeignClient;
    private final TurService turService;
    private final MusteriMapper musteriMapper;

    public void insert(MusteriDto musteriDto) {
        musteriRepository.save(musteriMapper.getSource(musteriDto));
    }

    public List<MusteriDto> findAll() {
        List<Musteri> musteriList = musteriRepository.findAll();
        return musteriMapper.getTargetList(musteriList);
    }

    public MusteriDto findById(Long id) {
        Musteri musteri = musteriRepository.findById(id)
                .orElseThrow(() -> new BulunamadiException("%s ile bir müşteri bulunamadı", id.toString()));
        return musteriMapper.getTarget(musteri);
    }

    public void delete(Long id) {
        musteriRepository.deleteById(id);
    }

    public void update(MusteriDto musteriDto) {
        musteriRepository.save(musteriMapper.getSource(musteriDto));
    }

    public MusteriDto save(MusteriDto musteriDto) {
        existsByEmail(musteriDto);
        turService.existsByTurId(musteriDto.getTurId());
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteri = musteriRepository.save(musteri);
        return musteriMapper.getTarget(musteri);
    }

    private void existsByEmail(MusteriDto musteriDto) {
        if (musteriRepository.existsByEmail(musteriDto.getEmail())) {
            throw new EmailDahaOnceAlinmisException("%s bu email daha önce alınmış.", musteriDto.getEmail());
        }
    }

    public byte[] generateSimpleReport(RaporOlusturmaDto raporOlusturmaDto) {
        DataResult<byte[]> dataResult = raporServiceFeignClient
                .generateSimpleReport(raporOlusturmaDto);
        return ResultDecoder.getDataResult(dataResult);
    }

    public void mailGonder(byte[] simpleReport) {

        MailGondermeDto mailGondermeDto = new MailGondermeDto();
        mailGondermeDto.setInputStream(simpleReport);
        mailGondermeDto.setSubject("bu rapor musteri servisten gelmiştir.");
        mailGondermeDto.setBody("bu rapor musteri servisten gelmiştir.");
        mailGondermeDto.setEmailTo("huseyinkaan.kizildag@gmail.com");

        SuccessResult successResult = mailSenderFeignClient.sendMailWithAttachment(mailGondermeDto);
        ResultDecoder.utilServiceCheck(successResult);
    }

    public byte[] musteriRaporla() {
        List<MusteriDto> musteriDtoList = findAll();
        List<RaporDto> raporDtoList = musteriMapper.getRaporDtoList(musteriDtoList);
        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto();
        raporOlusturmaDto.setRaporDtoList(raporDtoList);
        Map<String, Object> raporParametreleri = new HashMap<>();
        raporParametreleri.put("turAdi", "Ankara");
        raporOlusturmaDto.setRaporParametreleri(raporParametreleri);
        byte[] simpleReport = generateSimpleReport(raporOlusturmaDto);
//        new Thread(() -> mailGonder(simpleReport)).start();
        return simpleReport;
    }
}
