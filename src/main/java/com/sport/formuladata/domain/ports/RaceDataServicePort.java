package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.dtos.RaceDataDto;
import java.util.List;
import java.util.Optional;

public interface RaceDataServicePort {
    List<RaceDataDto> findAll();
    Optional<RaceDataDto> findById(Integer raceId);
    RaceDataDto create(RaceDataDto raceDataDto);
    RaceDataDto update(Integer raceId, RaceDataDto raceDataDto);
    void delete(Integer raceId);
    List<RaceDataDto> findAllByRaceId(Integer raceId);
}