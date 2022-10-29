package com.marul.satis;

import com.marul.dto.satis.SatisDto;
import com.marul.dto.satis.SatisResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SatisMapper {
    Satis getEntity(SatisResponseDto satisResponseDto);

    SatisResponseDto getDto(Satis satis);

    List<Satis> getEntityList(List<SatisResponseDto> satisResponseDto);

    List<SatisResponseDto> getResponseDtoList(List<Satis> satis);

    List<SatisDto> getDtoList(List<Satis> satis);
}
