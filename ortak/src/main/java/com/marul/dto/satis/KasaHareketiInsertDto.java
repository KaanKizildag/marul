package com.marul.dto.satis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class KasaHareketiInsertDto {

    @NotNull
    private BigDecimal tutar;
    @NotBlank
    @NotNull
    private String aciklama;
}
