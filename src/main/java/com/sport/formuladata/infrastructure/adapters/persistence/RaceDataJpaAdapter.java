package com.sport.formuladata.infrastructure.adapters.persistence;

import com.sport.formuladata.domain.entities.RaceData;
import com.sport.formuladata.domain.entities.RaceDataId;
import com.sport.formuladata.domain.ports.RaceDataRepositoryPort;
import com.sport.formuladata.infrastructure.adapters.persistence.repositories.RaceDataRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class RaceDataJpaAdapter implements RaceDataRepositoryPort {
    private final RaceDataRepository repository;

    public RaceDataJpaAdapter(RaceDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public RaceData save(RaceData raceData) {
        return repository.save(raceData);
    }

    @Override
    public Optional<RaceData> findById(RaceDataId id) {
        return repository.findById(id);
    }

    @Override
    public List<RaceData> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(RaceData raceData) {
        repository.delete(raceData);
    }

    @Override
    public List<RaceData> findAllByRaceId(Integer raceId) {
        return repository.findAllByRaceId(raceId);
    }
}