/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.musteri;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author Kaan
 */
@Entity
@Table(name = "musteri")
@Getter
@Setter
public class Musteri implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "musteri_adi", nullable = false)
    private String musteriAdi;

    @Column(name = "telefon_no")
    private String telefonNo;

    @Column(name = "email")
    private String email;

    @Column(name = "teslimat_noktasi", nullable = false)
    private String teslimatNoktasi;

    @Column(name = "borc")
    private Double borc = 0D;

    @Column(name = "tur_id")
    private Long turId;
}
