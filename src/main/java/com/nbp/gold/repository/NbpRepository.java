package com.nbp.gold.repository;

import com.nbp.gold.model.GoldRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbpRepository extends JpaRepository<GoldRate, Long> {
}
