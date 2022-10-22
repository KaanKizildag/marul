package com.marul.dto.satis;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
