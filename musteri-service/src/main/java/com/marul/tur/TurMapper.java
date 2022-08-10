package com.marul.tur;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurMapper {

    Tur getSource(TurDto target);

    List<Tur> getSourceList(List<TurDto> targetList);

    TurDto getTarget(Tur source);

    List<TurDto> getTargetList(List<Tur> sourceList);
}
