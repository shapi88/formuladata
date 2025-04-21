package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.Circuit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircuitRepository extends JpaRepository<Circuit, String> {
}