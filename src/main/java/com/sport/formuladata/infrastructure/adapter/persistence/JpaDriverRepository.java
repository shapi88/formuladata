package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDriverRepository extends JpaRepository<DriverEntity, Integer> {
}