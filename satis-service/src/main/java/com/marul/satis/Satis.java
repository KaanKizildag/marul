package com.marul.satis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "satis")
@Getter
@Setter
@NoArgsConstructor
public class Satis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "musteri_id", nullable = false)
    private Long musteriId;

    @Column(name = "urun_id", nullable = false)
    private Long urunId;

    @Column(name = "satilan_adet", nullable = false)
    private Long satilanAdet;

    @Column(name = "satis_zamani", nullable = false)
    private LocalDateTime satisZamani;

    @Column(name = "satis_fiyati", nullable = false)
    private BigDecimal satisFiyati;
}
