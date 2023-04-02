package com.marul.satis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class SonSatisOzetiDto {
    private String musteriAdi;
    private String urunAdi;
    private BigDecimal satisTutari;
}
