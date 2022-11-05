package com.marul.satis.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SatisUrunDto {
    @NotNull
    private Long urunId;
    @NotNull
    @Positive
    private Long satilanAdet;
}
