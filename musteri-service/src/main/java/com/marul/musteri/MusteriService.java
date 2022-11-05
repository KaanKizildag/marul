/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;

import com.marul.dto.MailGondermeDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.dto.result.DataResult;
import com.marul.dto.result.SuccessResult;
import com.marul.exception.BulunamadiException;
import com.marul.exception.EmailDahaOnceAlinmisException;
import com.marul.tur.TurDto;
import com.marul.tur.TurService;
import com.marul.util.ResultDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kaan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MusteriService {

    private final MusteriRepository musteriRepository;
    private final RaporServiceFeignClient raporServiceFeignClient;
    private final MailSenderFeignClient mailSenderFeignClient;
    private final TurService turService;
    private final MusteriMapper musteriMapper;

    public List<MusteriDto> findAll() {
        List<Musteri> musteriList = musteriRepository.findAll();
        List<MusteriDto> musteriDtoList = musteriMapper.getTargetList(musteriList);
        musteriDtoList = musteriDtoList.stream().map(musteri -> {
            Long turId = musteri.getTurId();
            TurDto turDto = turService.findById(turId);
            musteri.setTurAdi(turDto.getTurAdi());
            return musteri;
        }).collect(Collectors.toList());
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
        Musteri deletedMusteri = musteriRepository.findById(id).orElseThrow(() -> new BulunamadiException("Müsteri bulunamadi"));
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
        turMevcutMuKontrol(musteriDto.getTurId());
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteri = musteriRepository.save(musteri);
        return musteriMapper.getTarget(musteri);
    }

    public MusteriDto update(MusteriDto musteriDto) {
        turMevcutMuKontrol(musteriDto.getTurId());
        musteriRepository.findById(musteriDto.getId()).orElseThrow(() -> new BulunamadiException("Müsteri bulunamadi"));
        Musteri musteri = musteriMapper.getSource(musteriDto);
        musteriRepository.save(musteri);
        return musteriDto;
    }

    private void turMevcutMuKontrol(long turId) {
        if (!turService.existsByTurId(turId)) {
            throw new BulunamadiException("%s id ile tur bulunamadı.", String.valueOf(turId));
        }
    }

    private void emailAlinmisMiKontrol(String email) {
        if (musteriRepository.existsByEmail(email)) {
            throw new EmailDahaOnceAlinmisException("%s bu email daha önce alınmış.", email);
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
        log.info("{} tane müşteri raporlanacak ", raporDtoList.size());
        RaporOlusturmaDto raporOlusturmaDto = new RaporOlusturmaDto();
        raporOlusturmaDto.setRaporAdi("musteri_email_rapor.jrxml");
        raporOlusturmaDto.setRaporDtoList(raporDtoList);
        Map<String, Object> raporParametreleri = new HashMap<>();
        raporParametreleri.put("turAdi", "Ankara");
        raporOlusturmaDto.setRaporParametreleri(raporParametreleri);
        log.info("rapor feign çağrılıyor.");
        byte[] simpleReport = generateSimpleReport(raporOlusturmaDto);
        log.info("rapor oluşturuldu rapor boyutu {}B ", simpleReport.length);
//        new Thread(() -> mailGonder(simpleReport)).start();
        return simpleReport;
    }
}
