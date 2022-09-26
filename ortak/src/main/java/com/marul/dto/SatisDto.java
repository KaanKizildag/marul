package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SatisDto {
    private Long id;
    @NotNull
    private Long musteriId;
    @NotNull
    private Long urunId;
    @Positive
    private Long satilanAdet;
}
