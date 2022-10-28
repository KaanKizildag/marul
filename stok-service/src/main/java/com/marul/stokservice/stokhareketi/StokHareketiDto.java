package com.marul.stokservice.stokhareketi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
