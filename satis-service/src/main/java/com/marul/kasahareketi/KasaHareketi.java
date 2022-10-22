package com.marul.kasahareketi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
public class KasaHareketi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "tutar")
    private BigDecimal tutar;


}
