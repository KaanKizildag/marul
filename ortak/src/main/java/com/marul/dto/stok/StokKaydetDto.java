package com.marul.dto.stok;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class StokKaydetDto {
    @NotNull
    private Long urunId;
    @PositiveOrZero
    private Long adet;

    private String aciklama;
}
