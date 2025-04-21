package com.sport.formuladata.infrastructure.adapters.persistence;

import com.sport.formuladata.domain.entities.Constructor;
import com.sport.formuladata.domain.ports.ConstructorRepositoryPort;
import com.sport.formuladata.infrastructure.adapters.persistence.repositories.ConstructorRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ConstructorJpaAdapter implements ConstructorRepositoryPort {
    private final ConstructorRepository repository;

    public ConstructorJpaAdapter(ConstructorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Constructor save(Constructor constructor) {
        return repository.save(constructor);
    }

    @Override
    public Optional<Constructor> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Constructor> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Constructor constructor) {
        repository.delete(constructor);
    }
}