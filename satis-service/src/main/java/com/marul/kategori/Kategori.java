package com.marul.kategori;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity(name = "kategori")
@Getter
@Setter
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kategori_adi")
    private String kategoriAdi;

}
