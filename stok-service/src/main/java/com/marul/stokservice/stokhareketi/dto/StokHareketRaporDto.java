package com.marul.stokservice.stokhareketi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StokHareketRaporDto {
    private String urunAdi;
    private Long miktar;
    private Date hareketZamani;
    private String aciklama;
}
