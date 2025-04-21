package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.entities.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverRepositoryPort {
    Driver save(Driver driver);
    Optional<Driver> findById(String id);
    List<Driver> findAll();
    void delete(Driver driver);
}