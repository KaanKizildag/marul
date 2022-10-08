package com.marul.dto.stok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
public class StokGuncelleDto {
    private Long id;
    @NotNull
    private Long urunId;
    @Positive
    private Long adet;

    private String aciklama;
}
