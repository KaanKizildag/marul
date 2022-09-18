package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatisDto {
    private Long id;
    private Long musteriId;
    // todo UrunDto eklenecek.
    private Long urunId;
    private Long satilanAdet;
}
