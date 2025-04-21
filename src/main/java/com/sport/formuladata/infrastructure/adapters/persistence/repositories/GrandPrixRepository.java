package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.GrandPrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandPrixRepository extends JpaRepository<GrandPrix, String> {
}