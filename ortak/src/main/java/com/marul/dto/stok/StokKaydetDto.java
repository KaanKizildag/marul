package com.marul.dto.stok;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StokKaydetDto {
    @NotNull
    private Long urunId;
    @PositiveOrZero
    private Long adet;

    private String aciklama;
}
