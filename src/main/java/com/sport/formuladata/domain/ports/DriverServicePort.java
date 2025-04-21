package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.dtos.DriverDto;
import java.util.List;
import java.util.Optional;

public interface DriverServicePort {
    DriverDto create(DriverDto driverDto);
    Optional<DriverDto> findById(String id);
    List<DriverDto> findAll();
    DriverDto update(String id, DriverDto driverDto);
    void delete(String id);
}