package com.marul.stokservice.stokhareketi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StokHareketRaporDto {
    private String urunAdi;
    private Long miktar;
    private LocalDateTime hareketZamani;
}
