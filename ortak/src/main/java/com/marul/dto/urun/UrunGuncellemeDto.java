package com.marul.dto.urun;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
