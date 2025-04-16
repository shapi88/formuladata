package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSessionRepository extends JpaRepository<SessionEntity, Integer> {
}