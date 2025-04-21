package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, String> {
}