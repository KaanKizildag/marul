package com.marul.dto.rapor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RaporDto implements Serializable {
    // burada tanımlanan field'lar jrxml dosyasındaki isimlerle aynı olmalıdır.
    private String musteriAdi;
    private String teslimatNoktasi;
    private Double borc;
    private String telefonNo;

    private String urunAdi;
    private Long miktar;
    private BigDecimal tutar;

    private LocalDateTime hareketZamani;
    private boolean satisMi;
}
