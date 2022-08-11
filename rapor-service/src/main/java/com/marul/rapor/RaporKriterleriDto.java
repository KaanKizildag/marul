package com.marul.rapor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RaporKriterleriDto implements Serializable {
    // burada tanımlanan field'lar jrxml dosyasındaki isimlerle aynı olmalıdır.
    private String musteriAdi;
    private String email;
    private String telefonNo;
}
