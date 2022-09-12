package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatisDto {
    private Long id;
    private MusteriDto musteriDto;
    // todo UrunDto eklenecek.
    private Long urunId;
}
