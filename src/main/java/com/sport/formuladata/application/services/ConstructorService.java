package com.sport.formuladata.application.services;

import com.sport.formuladata.domain.dtos.ConstructorDto;
import com.sport.formuladata.domain.entities.Constructor;
import com.sport.formuladata.domain.ports.ConstructorRepositoryPort;
import com.sport.formuladata.domain.ports.ConstructorServicePort;
import com.sport.formuladata.application.mappers.ConstructorMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstructorService implements ConstructorServicePort {
    private final ConstructorRepositoryPort repository;
    private final ConstructorMapper mapper;

    public ConstructorService(ConstructorRepositoryPort repository, ConstructorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConstructorDto create(ConstructorDto constructorDto) {
        Constructor constructor = mapper.toEntity(constructorDto);
        return mapper.toDto(repository.save(constructor));
    }

    @Override
    public Optional<ConstructorDto> findById(String id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<ConstructorDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConstructorDto update(String id, ConstructorDto constructorDto) {
        Constructor constructor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Constructor not found: " + id));
        mapper.updateEntity(constructorDto, constructor);
        return mapper.toDto(repository.save(constructor));
    }

    @Override
    public void delete(String id) {
        Constructor constructor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Constructor not found: " + id));
        repository.delete(constructor);
    }
}