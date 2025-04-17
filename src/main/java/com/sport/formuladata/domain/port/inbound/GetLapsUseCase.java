package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.Lap;

import java.util.List;

public interface GetLapsUseCase {
    List<Lap> getAllLaps();
}