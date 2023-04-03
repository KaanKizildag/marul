package com.marul.stokservice.stokhareketi;

import com.marul.dto.rapor.RaporDto;
import com.marul.stokservice.stokhareketi.dto.StokHareketRaporDto;
import com.marul.stokservice.stokhareketi.dto.StokHareketiDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StokHareketiMapper {
    StokHareketi getEntity(StokHareketiDto stokHareketiDto);

    StokHareketiDto getDto(StokHareketi stokHareketi);

    List<StokHareketi> getEntityList(List<StokHareketiDto> stostokHareketiList);

    List<StokHareketiDto> getDtoList(List<StokHareketi> stokHareketiList);

    List<RaporDto> getRaporDto(List<StokHareketRaporDto> stokHareketRaporDtoList);
}
