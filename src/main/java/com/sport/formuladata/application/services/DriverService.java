package com.sport.formuladata.application.services;

import com.sport.formuladata.domain.dtos.DriverDto;
import com.sport.formuladata.domain.entities.Driver;
import com.sport.formuladata.domain.ports.DriverRepositoryPort;
import com.sport.formuladata.domain.ports.DriverServicePort;
import com.sport.formuladata.application.mappers.DriverMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService implements DriverServicePort {
    private final DriverRepositoryPort repository;
    private final DriverMapper mapper;

    public DriverService(DriverRepositoryPort repository, DriverMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DriverDto create(DriverDto driverDto) {
        Driver driver = mapper.toEntity(driverDto);
        return mapper.toDto(repository.save(driver));
    }

    @Override
    public Optional<DriverDto> findById(String id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<DriverDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDto update(String id, DriverDto driverDto) {
        Driver driver = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found: " + id));
        mapper.updateEntity(driverDto, driver);
        return mapper.toDto(repository.save(driver));
    }

    @Override
    public void delete(String id) {
        Driver driver = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found: " + id));
        repository.delete(driver);
    }
}