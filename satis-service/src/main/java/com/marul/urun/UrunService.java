/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marul.urun;

import com.marul.dto.stok.StokDto;
import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import com.marul.exception.ZatenKayitliException;
import com.marul.kategori.Kategori;
import com.marul.kategori.KategoriService;
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
    private final StokFeignClient stokFeignClient;
    private final KategoriService kategoriService;

    public UrunDto save(UrunDto urunDto) {
        String urunAdi = urunDto.getUrunAdi();

        if (existsByUrunAdi(urunAdi)) {
            throw new ZatenKayitliException("%s adıyla bir ürün zaten sisteme kayıtlı", urunAdi);
        }

        Kategori kategori = kategoriService.findById_JPA(urunDto.getKategoriId());
        Urun urun = urunMapper.getEntity(urunDto);
        urun.setKategori(kategori);
        urun = urunRepository.save(urun);
        varsayilanStokAtamasi(urun.getId());
        return urunMapper.getDto(urun);
    }

    private void varsayilanStokAtamasi(Long urunId) {
        long varsayilanStok = 0L;
        StokDto stokDto = new StokDto();
        stokDto.setUrunId(urunId);
        stokDto.setAdet(varsayilanStok);
        stokFeignClient.save(stokDto);
    }

    public boolean existsByUrunAdi(String urunAdi) {
        return urunRepository.existsByUrunAdi(urunAdi);
    }

    public boolean existsById(Long id) {
        return urunRepository.existsById(id);
    }

    public List<UrunDto> findAll() {
        List<Urun> urunList = urunRepository.findAll();
        List<UrunDto> urunDtoList = urunMapper.getDtoList(urunList);

        return urunDtoList;
    }

    public UrunDto findById(Long id) {
        Urun urun = urunRepository.findById(id)
                .orElseThrow(() -> new BulunamadiException("%s id ile ürün bulunamadı", id.toString()));
        return urunMapper.getDto(urun);
    }
}
