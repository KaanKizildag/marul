package com.marul.satis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SatisRepository extends JpaRepository<Satis, Long> {

    @Query("From Satis s Where s.musteriId = :musteriId")
    List<Satis> findByMusteriId(@Param("musteriId") Long musteriId);

    @Query("Select u.urunAdi " +
            "From Satis s, Urun u " +
            "Where s.urunId = u.id " +
            "And s.id = :satisId")
    Optional<String> findUrunAdiBySatisId(@Param("satisId") Long satisId);

}
