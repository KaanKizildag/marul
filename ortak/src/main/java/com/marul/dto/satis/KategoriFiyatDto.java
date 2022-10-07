package com.marul.dto.satis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class KategoriFiyatDto {
    private Date baslangicTarihi;
    private Date bitisTarihi;
    private String kategoriAdi;
    private BigDecimal toplamSatisTutari;
    private boolean satisArttiMi;
}
