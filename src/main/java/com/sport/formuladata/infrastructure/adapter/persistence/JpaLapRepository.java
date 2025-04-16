package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.LapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLapRepository extends JpaRepository<LapEntity, Integer> {
}