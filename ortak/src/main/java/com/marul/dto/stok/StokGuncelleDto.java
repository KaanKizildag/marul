package com.marul.dto.stok;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


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
