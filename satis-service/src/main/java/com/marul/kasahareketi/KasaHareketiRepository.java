package com.marul.kasahareketi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KasaHareketiRepository extends JpaRepository<KasaHareketi, Long> {
    @Query("Select sum(tutar) From KasaHareketi")
    Long kasaTutarToplam();
}
