package com.marul.musteri;

import com.marul.dto.MusteriDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusteriMapper {

    MusteriDto getTarget(Musteri musteri);

    @Mapping(target = "borc", expression = "java( musteri.getBorc() / 100 )")
    Musteri getSource(MusteriDto musteri);

    List<Musteri> getSourceList(List<MusteriDto> musteri);

    List<MusteriDto> getTargetList(List<Musteri> musteri);
}
