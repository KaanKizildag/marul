/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.marul.urun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kaan
 */
@Repository
public interface UrunRepository extends JpaRepository<Urun, Long> {
    boolean existsByUrunAdi(String urunAdi);
}
