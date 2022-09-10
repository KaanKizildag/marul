package com.marul.stokservice.stok;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StokMapper {
    Stok getEntity(StokDto stokDto);

    List<Stok> getEntityList(List<StokDto> stokDtoList);

    StokDto getDto(Stok stok);

    List<StokDto> getDtoList(List<Stok> stokList);
}
