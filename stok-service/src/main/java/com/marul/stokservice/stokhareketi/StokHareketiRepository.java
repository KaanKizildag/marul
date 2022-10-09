package com.marul.stokservice.stokhareketi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StokHareketiRepository extends JpaRepository<StokHareketi, Long> {
    List<StokHareketi> findStokHareketiByHareketZamaniBetween(LocalDateTime baslangic, LocalDateTime bitis);

    List<StokHareketi> findStokHareketiByHareketZamaniBefore(LocalDateTime bitis);

    @Query("From StokHareketi order by id desc")
    List<StokHareketi> findAllOrderByIdDesc();
}
