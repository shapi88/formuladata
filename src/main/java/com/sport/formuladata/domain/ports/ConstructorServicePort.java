package com.sport.formuladata.domain.ports;

import com.sport.formuladata.domain.dtos.ConstructorDto;
import java.util.List;
import java.util.Optional;

public interface ConstructorServicePort {
    ConstructorDto create(ConstructorDto constructorDto);
    Optional<ConstructorDto> findById(String id);
    List<ConstructorDto> findAll();
    ConstructorDto update(String id, ConstructorDto constructorDto);
    void delete(String id);
}