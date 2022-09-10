package com.marul.stok;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StokMapper {
    Stok getEntity(StokDto stokDto);

    List<Stok> getEntityList(List<StokDto> stokDtoList);

    StokDto getDto(Stok stok);

    List<StokDto> getDtoList(List<Stok> stokList);
}
