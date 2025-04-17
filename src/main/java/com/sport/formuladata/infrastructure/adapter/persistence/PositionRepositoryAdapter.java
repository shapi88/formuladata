package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Position;
import com.sport.formuladata.domain.port.outbound.PositionRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.PositionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class PositionRepositoryAdapter implements PositionRepositoryPort {

    private final JpaMeetingRepository jpaMeetingRepository;
    
    private static final Logger LOGGER = Logger.getLogger(PositionRepositoryPort.class.getName());
    private final JpaPositionRepository jpaRepository;
    private final JpaSessionRepository jpaSessionRepository;
    private final JpaDriverRepository jpaDriverRepository;

    public PositionRepositoryAdapter(
            JpaPositionRepository jpaRepository,
            JpaSessionRepository jpaSessionRepository,
            JpaDriverRepository jpaDriverRepository,
            JpaMeetingRepository jpaMeetingRepository
        ) {
        this.jpaRepository = jpaRepository;
        this.jpaSessionRepository = jpaSessionRepository;
        this.jpaDriverRepository = jpaDriverRepository;
        this.jpaMeetingRepository = jpaMeetingRepository;
    }

    @Override
    public void saveAll(List<Position> positions) {
        List<PositionEntity> entities = positions.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Position> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private PositionEntity toEntity(Position position) {
        PositionEntity entity = new PositionEntity();
        if (position.sessionKey() != null) {
            jpaSessionRepository.findById(position.sessionKey())
                    .ifPresentOrElse(
                        entity::setSession,
                        () -> LOGGER.warning("No SessionEntity found for session_key: " + position.sessionKey())
                    );
        }
        if (position.driverNumber() != null) {
            jpaDriverRepository.findByDriverNumber(position.driverNumber())
                    .ifPresentOrElse(
                        entity::setDriver,
                        () -> LOGGER.warning("No DriverEntity found for driver_number: " + position.driverNumber())
                    );
        }
        if (position.meetingKey() != null) {
            jpaMeetingRepository.findById(position.meetingKey())
                    .ifPresentOrElse(
                        entity::setMeeting,
                        () -> LOGGER.warning("No DriverEntity found for driver_number: " + position.meetingKey())
                    );
        }
        entity.setPosition(position.position());
        entity.setDate(position.date());
        return entity;
    }

    private Position toDomain(PositionEntity entity) {
        return new Position(
                entity.getSession().getSessionKey(),
                entity.getSession().getSessionKey(),
                entity.getDriver().getDriverNumber(),
                entity.getPosition(),
                entity.getDate()
        );
    }
}