package com.marul.satis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "satis")
@Getter
@Setter
public class Satis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "musteri_id")
    private Long musteriId;

    @Column(name = "urun_id")
    private Long urunId;
}
