package com.sport.formuladata.infrastructure.adapters.persistence;

import com.sport.formuladata.domain.entities.Driver;
import com.sport.formuladata.domain.ports.DriverRepositoryPort;
import com.sport.formuladata.infrastructure.adapters.persistence.repositories.DriverRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class DriverJpaAdapter implements DriverRepositoryPort {
    private final DriverRepository repository;

    public DriverJpaAdapter(DriverRepository repository) {
        this.repository = repository;
    }

    @Override
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    public Optional<Driver> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Driver driver) {
        repository.delete(driver);
    }
}