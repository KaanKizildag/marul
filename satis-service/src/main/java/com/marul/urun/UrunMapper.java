package com.marul.urun;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UrunMapper {
    UrunDto getDto(Urun urun);

    List<UrunDto> getDtoList(List<Urun> urun);

    Urun getEntity(UrunDto urunDto);
}
