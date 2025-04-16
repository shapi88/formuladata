package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.CarDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCarDataRepository extends JpaRepository<CarDataEntity, Integer> {
}