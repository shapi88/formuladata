package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Driver;
import com.sport.formuladata.domain.port.outbound.DriverRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverRepositoryAdapter implements DriverRepositoryPort {
    private final JpaDriverRepository jpaRepository;

    public DriverRepositoryAdapter(JpaDriverRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<Driver> drivers) {
        List<DriverEntity> entities = drivers.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Driver> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private DriverEntity toEntity(Driver driver) {
        DriverEntity entity = new DriverEntity();
        entity.setDriverNumber(driver.driverNumber());
        entity.setFullName(driver.fullName());
        entity.setTeamName(driver.teamName());
        entity.setTeamColour(driver.teamColour());
        entity.setCountryCode(driver.countryCode());
        return entity;
    }

    private Driver toDomain(DriverEntity entity) {
        return new Driver(
                entity.getDriverNumber(),
                entity.getFullName(),
                entity.getTeamName(),
                entity.getTeamColour(),
                entity.getCountryCode()
        );
    }
}