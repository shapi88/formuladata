package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.entities.Race;
import java.util.List;
import java.util.Optional;

public interface RaceRepositoryPort {
    Race save(Race race);
    Optional<Race> findById(Integer id);
    List<Race> findAll();
    void delete(Race race);
}