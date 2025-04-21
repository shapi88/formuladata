package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Integer> {
}