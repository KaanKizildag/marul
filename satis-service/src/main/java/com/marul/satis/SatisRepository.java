package com.marul.satis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SatisRepository extends JpaRepository<Satis, Long> {

    @Query("From Satis s Where s.musteriId = :musteriId And s.grup_id = :grupId")
    List<Satis> findByMusteriIdAndGrupId(@Param("musteriId") Long musteriId, @Param("grupId") UUID grupId);

    @Query("Select u.urunAdi " +
            "From Satis s, Urun u " +
            "Where s.urunId = u.id " +
            "And s.id = :satisId")
    Optional<String> findUrunAdiBySatisId(@Param("satisId") Long satisId);

    @Query("From Satis s Where s.satisZamani between :baslangicZamani And :bitisZamani")
    List<Satis> haftalikSatisiGetir(@Param("baslangicZamani") LocalDateTime baslangicZamani, @Param("bitisZamani") LocalDateTime bitisZamani);
}
