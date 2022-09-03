package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.dto.RaporDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusteriMapper {

    MusteriDto getTarget(Musteri musteri);

    @Mapping(target = "borc", expression = "java( musteriDto.getBorc() / 100 )")
    Musteri getSource(MusteriDto musteriDto);

    List<Musteri> getSourceList(List<MusteriDto> musteriDtoList);

    List<MusteriDto> getTargetList(List<Musteri> musteriList);

    RaporDto getRaporDto(MusteriDto musteriDto);

    List<RaporDto> getRaporDtoList(List<MusteriDto> musteriDtoList);
}
