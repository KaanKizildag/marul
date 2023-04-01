package com.marul.dto.satis;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SatisResponseDto {

    private Long id;
    @NotNull
    private Long musteriId;
    @NotEmpty
    private List<SatisDto> satisDtoList;
}
