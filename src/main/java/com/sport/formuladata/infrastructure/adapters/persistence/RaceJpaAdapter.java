package com.sport.formuladata.infrastructure.adapters.persistence;

import com.sport.formuladata.domain.entities.Race;
import com.sport.formuladata.domain.ports.RaceRepositoryPort;
import com.sport.formuladata.infrastructure.adapters.persistence.repositories.RaceRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class RaceJpaAdapter implements RaceRepositoryPort {
    private final RaceRepository repository;

    public RaceJpaAdapter(RaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Race save(Race race) {
        return repository.save(race);
    }

    @Override
    public Optional<Race> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Race> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Race race) {
        repository.delete(race);
    }
}