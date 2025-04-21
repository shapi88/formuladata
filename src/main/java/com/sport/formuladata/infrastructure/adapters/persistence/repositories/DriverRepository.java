package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}