package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.Lap;

import java.util.List;

public interface LapRepositoryPort {
    void saveAll(List<Lap> laps);
}
