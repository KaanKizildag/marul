package com.marul.stokservice.stokhareketi;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StokHareketiDto {

    @NotNull
    private Long stokId;
    @NotNull
    private Long miktar;
    @NotNull
    private LocalDateTime hareketZamani;
    @NotNull
    private boolean satisMi;
    private String aciklama;
}
