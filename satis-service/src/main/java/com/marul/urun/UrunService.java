/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marul.urun;

import com.marul.dto.stok.StokKaydetDto;
import com.marul.dto.urun.UrunDto;
import com.marul.dto.urun.UrunGuncellemeDto;
import com.marul.exception.AlreadyExistsException;
import com.marul.exception.NotFoundException;
import com.marul.integration.StokServiceIntegration;
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
    private final StokServiceIntegration stokServiceIntegration;
    private final KategoriService kategoriService;

    public UrunDto save(UrunDto urunDto) {
        String urunAdi = urunDto.getUrunAdi();
        if (existsByUrunAdi(urunAdi)) {
            throw new AlreadyExistsException("%s adıyla bir ürün zaten sisteme kayıtlı", urunAdi);
        }
        Kategori kategori = kategoriService.findById_JPA(urunDto.getKategoriId());
        Urun urun = urunMapper.getEntity(urunDto);
        urun.setKategori(kategori);
        urun = urunRepository.save(urun);
        varsayilanStokAtamasi(urun.getId());
        return urunMapper.getDto(urun);
    }

    public UrunDto guncelle(UrunGuncellemeDto urunGuncellemeDto) {
        Kategori kategori = kategoriService.findById_JPA(urunGuncellemeDto.getKategoriId());
        Urun urun = urunMapper.getEntity(urunGuncellemeDto);
        urun.setKategori(kategori);
        urun = urunRepository.save(urun);
        return urunMapper.getDto(urun);
    }

    private void varsayilanStokAtamasi(Long urunId) {
        long varsayilanStok = 0L;
        StokKaydetDto stokKaydetDto = new StokKaydetDto();
        stokKaydetDto.setAdet(varsayilanStok);
        stokKaydetDto.setUrunId(urunId);
        stokKaydetDto.setAciklama("Ürün ekleme");
        stokServiceIntegration.save(stokKaydetDto);
    }

    public boolean existsByUrunAdi(String urunAdi) {
        return urunRepository.existsByUrunAdi(urunAdi);
    }

    public boolean existsById(Long id) {
        return urunRepository.existsById(id);
    }

    public List<UrunDto> findAll() {
        List<Urun> urunList = urunRepository.findAll();
        return urunMapper.getDtoList(urunList);
    }

    public UrunDto findById(Long id) {
        Urun urun = urunRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("%s id ile ürün bulunamadı", id.toString()));
        return urunMapper.getDto(urun);
    }

    public void deleteById(Long id) {
        Urun urun = urunRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("%s id ile ürün bulunamadı", id.toString()));
        stokServiceIntegration.stokSil(urun.getId());
        urunRepository.delete(urun);
    }
}
