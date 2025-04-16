package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.IntervalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIntervalRepository extends JpaRepository<IntervalEntity, Integer> {
}