package com.marul.kategori;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KategoriMapper {
    Kategori getEntity(KategoriDto kategoriDto);

    KategoriDto getDto(Kategori kategori);
}
