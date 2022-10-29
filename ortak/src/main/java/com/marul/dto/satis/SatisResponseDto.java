package com.marul.dto.satis;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
