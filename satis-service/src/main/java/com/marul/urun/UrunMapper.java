package com.marul.urun;

import com.marul.dto.urun.UrunDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UrunMapper {
    @Mapping(source = "kategori.id", target = "kategoriId")
    @Mapping(source = "kategori.kategoriAdi", target = "kategoriAdi")
    UrunDto getDto(Urun urun);

    @Mapping(source = "kategori.kategoriAdi", target = "kategoriAdi")
    @Mapping(source = "kategori.id", target = "kategoriId")
    List<UrunDto> getDtoList(List<Urun> urun);

    @Mapping(source = "kategoriId", target = "kategori.id")
    @Mapping(source = "kategoriAdi", target = "kategori.kategoriAdi")
    Urun getEntity(UrunDto urunDto);
}
