package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.Driver;

import java.util.List;

public interface GetDriversUseCase {
    List<Driver> getAllDrivers();
}