package com.marul.satis.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatisUrunDto {
    @NotNull
    private Long urunId;
    @NotNull
    @Positive
    private Long satilanAdet;
}
