package com.marul.satis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatisRepository extends JpaRepository<Satis, Long> {
}
