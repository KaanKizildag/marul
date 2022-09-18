package com.marul.satis;

import com.marul.dto.SatisDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SatisMapper {
    Satis getEntity(SatisDto satisDto);
    SatisDto getDto(Satis satis);

    List<Satis> getEntityList(List<SatisDto> satisDto);
    List<SatisDto> getDtoList(List<Satis> satis);
}
