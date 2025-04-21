package com.sport.formuladata.application.services;

import com.sport.formuladata.domain.dtos.RaceDto;
import com.sport.formuladata.domain.entities.Race;
import com.sport.formuladata.domain.ports.RaceRepositoryPort;
import com.sport.formuladata.domain.ports.RaceServicePort;
import com.sport.formuladata.application.mappers.RaceMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceService implements RaceServicePort {
    private final RaceRepositoryPort repository;
    private final RaceMapper mapper;

    public RaceService(RaceRepositoryPort repository, RaceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RaceDto create(RaceDto raceDto) {
        Race race = mapper.toEntity(raceDto);
        return mapper.toDto(repository.save(race));
    }

    @Override
    public Optional<RaceDto> findById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<RaceDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RaceDto update(Integer id, RaceDto raceDto) {
        Race race = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Race not found: " + id));
        mapper.updateEntity(raceDto, race);
        return mapper.toDto(repository.save(race));
    }

    @Override
    public void delete(Integer id) {
        Race race = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Race not found: " + id));
        repository.delete(race);
    }
}