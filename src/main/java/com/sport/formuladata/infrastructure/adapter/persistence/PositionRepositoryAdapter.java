package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Position;
import com.sport.formuladata.domain.port.outbound.PositionRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.PositionEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionRepositoryAdapter implements PositionRepositoryPort {
    private final JpaPositionRepository jpaRepository;

    public PositionRepositoryAdapter(JpaPositionRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<Position> positions) {
        List<PositionEntity> entities = positions.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    private PositionEntity toEntity(Position position) {
        PositionEntity entity = new PositionEntity();
        entity.setPositionId(position.positionId());
        if (position.session() != null) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionKey(position.session().sessionKey());
            entity.setSession(sessionEntity);
        }
        if (position.driver() != null) {
            DriverEntity driverEntity = new DriverEntity();
            driverEntity.setDriverNumber(position.driver().driverNumber());
            entity.setDriver(driverEntity);
        }
        entity.setPosition(position.position());
        entity.setDate(position.date());
        return entity;
    }
}