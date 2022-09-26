package com.marul.dto.stok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
