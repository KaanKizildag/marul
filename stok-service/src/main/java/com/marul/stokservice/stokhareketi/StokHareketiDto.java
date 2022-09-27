package com.marul.stokservice.stokhareketi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class StokHareketiDto {

    @NotNull
    private Long stokId;
    @NotNull
    private Long adet;
}
