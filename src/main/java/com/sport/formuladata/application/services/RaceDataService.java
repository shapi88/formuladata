package com.sport.formuladata.application.services;

import com.sport.formuladata.application.mappers.RaceDataMapper;
import com.sport.formuladata.domain.dtos.RaceDataDto;
import com.sport.formuladata.domain.entities.RaceData;
import com.sport.formuladata.domain.entities.RaceDataId;
import com.sport.formuladata.domain.ports.RaceDataRepositoryPort;
import com.sport.formuladata.domain.ports.RaceDataServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceDataService implements RaceDataServicePort {
    private final RaceDataRepositoryPort repository;
    private final RaceDataMapper mapper;

    public RaceDataService(RaceDataRepositoryPort repository, RaceDataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RaceDataDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RaceDataDto> findById(Integer raceId) {
        return repository.findById(new RaceDataId(raceId))
                .map(mapper::toDto);
    }

    @Override
    public RaceDataDto create(RaceDataDto raceDataDto) {
        RaceData raceData = mapper.toEntity(raceDataDto);
        // Set relationships (driver, constructor) using repositories if needed
        return mapper.toDto(repository.save(raceData));
    }

    @Override
    public RaceDataDto update(Integer raceId, RaceDataDto raceDataDto) {
        RaceData raceData = repository.findById(new RaceDataId(raceId))
                .orElseThrow(() -> new IllegalArgumentException("RaceData not found"));
        mapper.updateEntity(raceDataDto, raceData);
        return mapper.toDto(repository.save(raceData));
    }

    @Override
    public void delete(Integer raceId) {
        RaceData raceData = repository.findById(new RaceDataId(raceId))
                .orElseThrow(() -> new IllegalArgumentException("RaceData not found"));
        repository.delete(raceData);
    }

    @Override
    public List<RaceDataDto> findAllByRaceId(Integer raceId) {
        return repository.findAllByRaceId(raceId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}