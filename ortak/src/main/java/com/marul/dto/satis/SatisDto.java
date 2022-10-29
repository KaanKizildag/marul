package com.marul.dto.satis;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SatisDto {

    @NotNull
    private Long musteriId;
    @NotNull
    private Long urunId;
    @NotNull
    @Positive
    private Long satilanAdet;
}
