package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokGuncelleDto;
import com.marul.dto.stok.StokKaydetDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StokMapper {
    Stok getEntity(StokDto stokDto);

    Stok getEntity(StokKaydetDto stokDto);

    Stok getEntity(StokGuncelleDto stokDto);

    List<Stok> getEntityList(List<StokDto> stokDtoList);

    StokDto getDto(Stok stok);

    List<StokDto> getDtoList(List<Stok> stokList);
}
