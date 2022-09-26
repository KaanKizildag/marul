package com.marul.dto.urun;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
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
    @Min(0L)
    private int kdv;
}
