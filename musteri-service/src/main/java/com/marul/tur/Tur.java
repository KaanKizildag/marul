/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.tur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Kaan
 */
@Entity
@Getter
@Setter
@Table(name = "tur")
@NoArgsConstructor
@AllArgsConstructor
public class Tur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tur_adi")
    private String turAdi;

    public Tur(String turAdi) {
        this.turAdi = turAdi;
    }
}
