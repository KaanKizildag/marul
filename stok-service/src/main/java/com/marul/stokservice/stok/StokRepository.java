package com.marul.stokservice.stok;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StokRepository extends JpaRepository<Stok, Long> {
    @Query("Select (s.adet >= :stok) From Stok s Where s.urunId = :urunId")
    Boolean yeterliStokVarMi(@Param("urunId") Long urunId,
                             @Param("stok") Long stok);

    Optional<Stok> findByUrunId(Long urunId);
}
