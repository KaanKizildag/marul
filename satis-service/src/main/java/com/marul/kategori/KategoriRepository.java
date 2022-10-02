package com.marul.kategori;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    //    @Query("Select rownum > 0 " +
//            "FROM (Select * " +
//            "From Kategori k " +
//            "WHERE k.karegoriAdi = :kategoriAdi) ")
    Boolean existsByKategoriAdi(@Param("kategoriAdi") String kategoriAdi);
}
