package com.marul.stokservice.stokhareketi;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StokHareketiMapper {
    StokHareketi getEntity(StokHareketiDto stokHareketiDto);

    StokHareketiDto getDto(StokHareketi stokHareketi);
}
