package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.dtos.RaceDto;
import java.util.List;
import java.util.Optional;

public interface RaceServicePort {
    RaceDto create(RaceDto raceDto);
    Optional<RaceDto> findById(Integer id);
    List<RaceDto> findAll();
    RaceDto update(Integer id, RaceDto raceDto);
    void delete(Integer id);
}