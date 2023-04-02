package com.marul.kategori;

import com.marul.exception.NotFoundException;
import com.marul.exception.ZatenKayitliException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KategoriService {

    private final KategoriRepository kategoriRepository;
    private final KategoriMapper kategoriMapper;

    public Kategori findById_JPA(Long id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("%s id ile kategori bulunamadı", id.toString()));
    }

    public KategoriDto findById(Long id) {
        Kategori kategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("%s id ile kategori bulunamadı", id.toString()));
        return kategoriMapper.getDto(kategori);
    }

    public KategoriDto save(KategoriDto kategoriDto) {
        Kategori kategori = kategoriMapper.getEntity(kategoriDto);
        String kategoriAdi = kategoriDto.getKategoriAdi();
        Boolean kategoriVarMi = kategoriRepository.existsByKategoriAdi(kategoriAdi);
        if (Boolean.TRUE.equals(kategoriVarMi)) {
            log.warn("{} isimli kategori zaten kayıtlı", kategoriAdi);
            throw new ZatenKayitliException("%s kategori zaten kayıtlı", kategoriAdi);
        }
        kategori = kategoriRepository.save(kategori);
        return kategoriMapper.getDto(kategori);
    }

    public List<KategoriDto> findAll() {
        List<Kategori> kategoriList = kategoriRepository.findAll();
        return kategoriMapper.getDtoList(kategoriList);
    }
}
