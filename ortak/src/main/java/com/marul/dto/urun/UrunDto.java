package com.marul.dto.urun;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UrunDto {
    private Long id;
    private String urunAdi;
    private BigDecimal fiyat;
    private int kdv;
}
