package com.marul.dto.stok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StokDto {
    private Long id;
    private Long urunId;
    private Long adet;
}
