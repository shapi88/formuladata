package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.CarData;

import java.util.List;

public interface GetCarDataUseCase {
    List<CarData> getAllCarData();
}