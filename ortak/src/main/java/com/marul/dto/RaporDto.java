package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RaporDto implements Serializable {
    // burada tanımlanan field'lar jrxml dosyasındaki isimlerle aynı olmalıdır.
    private String musteriAdi;
    private String teslimatNoktasi;
    private Double borc;
    private String telefonNo;
}
