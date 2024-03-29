package com.marul.stokservice.stok;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stok")
@Getter
@Setter
public class Stok {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "urun_id", unique = true)
    private Long urunId;

    @Column(name = "adet")
    private Long adet;
}