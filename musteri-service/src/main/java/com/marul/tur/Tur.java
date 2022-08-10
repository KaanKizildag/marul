/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marul.tur;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kaan
 */
@Entity
@Getter
@Setter
@Table(name = "tur")
@NoArgsConstructor
public class Tur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tur_adi")
    private String turAdi;
//
//    public Tur(Long id) {
//        this.id = id;
//    }

}
