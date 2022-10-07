/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.urun;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.marul.kategori.Kategori;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Kaan
 */
@Entity
@Table(name = "urun")
@Getter
@Setter
public class Urun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "urunAdi", nullable = false, unique = true)
    private String urunAdi;

    @Column(name = "fiyat", nullable = false)
    private BigDecimal fiyat;

    @Column(name = "kdv")
    private int kdv;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Kategori.class)
    @JoinColumn(name="kategori_id")
    private Kategori kategori;

    @Column(name = "barkod")
    private String barkod;

}
