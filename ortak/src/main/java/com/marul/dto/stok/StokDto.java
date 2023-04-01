package com.marul.dto.stok;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class StokDto {
    private Long id;
    @NotNull
    private Long urunId;
    @Positive
    private Long adet;
}
