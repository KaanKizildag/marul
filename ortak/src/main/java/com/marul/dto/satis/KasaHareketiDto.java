package com.marul.dto.satis;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class KasaHareketiDto {
    private Long id;

    private String aciklama;

    private BigDecimal tutar;
}
