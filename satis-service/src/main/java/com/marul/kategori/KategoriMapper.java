package com.marul.kategori;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KategoriMapper {
    Kategori getEntity(KategoriDto kategoriDto);

    KategoriDto getDto(Kategori kategori);

    List<Kategori> getEntityList(List<KategoriDto> kategoriDto);

    List<KategoriDto> getDtoList(List<Kategori> kategori);


}
