package com.marul.kasahareketi;

import com.marul.dto.satis.KasaHareketiDto;
import com.marul.dto.satis.KasaHareketiInsertDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KasaHareketiMapper {
    KasaHareketi getEntity(KasaHareketiInsertDto kasaHareketiInsertDto);

    KasaHareketiDto getDto(KasaHareketi kasaHareketiInsertDto);
}
