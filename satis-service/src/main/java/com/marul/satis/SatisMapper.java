package com.marul.satis;

import com.marul.dto.SatisDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SatisMapper {
    @Mapping(target = "musteriId", source = "musteriDto.id")
    Satis getEntity(SatisDto satisDto);
    @Mapping(target = "musteriDto.id", source = "satis.musteriId")
    SatisDto getDto(Satis satis);

    List<Satis> getEntityList(List<SatisDto> satisDto);
    List<SatisDto> getDtoList(List<Satis> satis);
}
