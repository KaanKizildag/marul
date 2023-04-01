package com.marul.dto.urun;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UrunGuncellemeDto {

    private Long id;
    @NotBlank
    private String urunAdi;
    @Positive
    private BigDecimal fiyat;
    @Positive
    private int kdv;
    @NotNull
    private Long kategoriId;
}
