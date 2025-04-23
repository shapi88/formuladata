package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.entities.RaceData;
import com.sport.formuladata.domain.entities.RaceDataId;
import java.util.List;
import java.util.Optional;

public interface RaceDataRepositoryPort {
    RaceData save(RaceData raceData);
    Optional<RaceData> findById(RaceDataId id);
    List<RaceData> findAll();
    void delete(RaceData raceData);
    List<RaceData> findAllByRaceId(Integer raceId);
}