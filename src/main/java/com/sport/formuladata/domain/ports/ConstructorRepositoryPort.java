package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.entities.Constructor;
import java.util.List;
import java.util.Optional;

public interface ConstructorRepositoryPort {
    Constructor save(Constructor constructor);
    Optional<Constructor> findById(String id);
    List<Constructor> findAll();
    void delete(Constructor constructor);
}