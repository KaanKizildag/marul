package com.marul.dto.satis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class KategoriSatisAnalizDto {
    private String kategoriAdi;
    private BigDecimal toplamSatisTutari;
}
