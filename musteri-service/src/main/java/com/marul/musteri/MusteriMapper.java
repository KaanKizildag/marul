package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.rapor.RaporDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusteriMapper {

    MusteriDto getTarget(Musteri musteri);

    Musteri getSource(MusteriDto musteriDto);

    List<Musteri> getSourceList(List<MusteriDto> musteriDtoList);

    List<MusteriDto> getTargetList(List<Musteri> musteriList);

    RaporDto getRaporDto(MusteriDto musteriDto);

    List<RaporDto> getRaporDtoList(List<MusteriDto> musteriDtoList);
}