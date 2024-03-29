package com.marul.stokservice.stokhareketi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
@Table(name = "stok_hareketi")
@Getter
@Setter
public class StokHareketi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stok_id")
    private Long stokId;

    @Column(name = "hareket_zamani")
    private LocalDateTime hareketZamani;

    @Column(name = "miktar")
    private Long miktar;

    @Formula("miktar > 0")
    private Boolean satisMi;

    @Column(name = "aciklama")
    private String aciklama;
}
