package com.marul.dto.urun;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class UrunDto {

    private Long id;
    @NotNull
    private String urunAdi;
    @Positive
    private BigDecimal fiyat;
    @Positive
    private int kdv;
    @NotNull
    private Long kategoriId;

    private String kategoriAdi;
}
