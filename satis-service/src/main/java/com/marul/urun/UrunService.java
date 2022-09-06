/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marul.urun;

import com.marul.exception.BulunamadiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaan
 */
@Service
@RequiredArgsConstructor
public class UrunService {

    private final UrunRepository urunRepository;
    private final UrunMapper urunMapper;

    public UrunDto save(UrunDto urunDto) {
        String urunAdi = urunDto.getUrunAdi();

        if (urunRepository.existsByUrunAdi(urunAdi)) {
            throw new BulunamadiException("%s adıyla bir ürün zaten sisteme kayıtlı", urunAdi);
        }

        Urun urun = urunMapper.getEntity(urunDto);
        urun = urunRepository.save(urun);
        return urunMapper.getDto(urun);
    }

    public List<UrunDto> findAll() {
        List<Urun> urunList = urunRepository.findAll();
        return urunMapper.getDtoList(urunList);
    }

    public UrunDto findById(Long id) {
        Urun urun = urunRepository.findById(id)
                .orElseThrow(() -> new BulunamadiException("%s id ile ürün bulunamadı"));
        return urunMapper.getDto(urun);
    }
}
