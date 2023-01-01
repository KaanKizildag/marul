package com.marul.kasahareketi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
