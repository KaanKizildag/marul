package com.marul.kasahareketi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "kasa_hareketi")
@Getter
@Setter
@NoArgsConstructor
public class KasaHareketi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "tutar")
    private BigDecimal tutar;
}
