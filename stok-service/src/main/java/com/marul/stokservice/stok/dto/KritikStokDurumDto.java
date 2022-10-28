package com.marul.stokservice.stok.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class KritikStokDurumDto {
    private Long urunId;
    private Long adet;
    private String urunAdi;
    private String kategoriAdi;
    private boolean kritikMi;
}
