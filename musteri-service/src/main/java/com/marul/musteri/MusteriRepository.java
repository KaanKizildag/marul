package com.marul.musteri;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {
    boolean existsByEmail(String email);
}
