package com.marul.satis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SonSatisOzetiDto {
    private String musteriAdi;
    private String urunAdi;
    private BigDecimal satisTutari;
}
