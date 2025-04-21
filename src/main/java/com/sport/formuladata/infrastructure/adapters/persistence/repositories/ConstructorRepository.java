package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructorRepository extends JpaRepository<Constructor, String> {
}