package com.marul.stokservice.stokhareketi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StokHareketiRepository extends JpaRepository<StokHareketi, Long> {
}
