package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.Driver;

import java.util.List;

public interface DriverRepositoryPort {
    void saveAll(List<Driver> drivers);
    List<Driver> findAll();
}