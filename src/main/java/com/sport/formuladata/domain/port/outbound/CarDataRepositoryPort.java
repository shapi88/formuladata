package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.CarData;

import java.util.List;

public interface CarDataRepositoryPort {
    void saveAll(List<CarData> carData);
}