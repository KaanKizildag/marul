package com.marul.dto.satis;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


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
