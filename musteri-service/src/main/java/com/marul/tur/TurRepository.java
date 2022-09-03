package com.marul.tur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurRepository extends JpaRepository<Tur, Long> {
    boolean existsByTurAdi(String turAdi);

    boolean existsById(Long id);
}
