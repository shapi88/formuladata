package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPositionRepository extends JpaRepository<PositionEntity, Integer> {
}