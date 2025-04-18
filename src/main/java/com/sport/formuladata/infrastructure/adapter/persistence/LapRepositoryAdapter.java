package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Lap;
import com.sport.formuladata.domain.port.outbound.LapRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.LapEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class LapRepositoryAdapter implements LapRepositoryPort {
    
    private static final Logger LOGGER = Logger.getLogger(LapRepositoryAdapter.class.getName());
    private final JpaLapRepository jpaRepository;
    private final JpaSessionRepository jpaSessionRepository;
    private final JpaDriverRepository jpaDriverRepository;

    public LapRepositoryAdapter(
            JpaLapRepository jpaRepository,
            JpaSessionRepository jpaSessionRepository,
            JpaDriverRepository jpaDriverRepository
        ) {
        this.jpaRepository = jpaRepository;
        this.jpaSessionRepository = jpaSessionRepository;
        this.jpaDriverRepository = jpaDriverRepository;
    }

    @Override
    public void saveAll(List<Lap> laps) {
        LOGGER.info("Saving " + laps.size() + " lap entries");
        List<LapEntity> entities = laps.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Lap> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private LapEntity toEntity(Lap lap) {
        LapEntity entity = new LapEntity();
        if (lap.sessionKey() != null) {
            jpaSessionRepository.findById(lap.sessionKey())
                    .ifPresentOrElse(
                        entity::setSession,
                        () -> LOGGER.warning("No SessionEntity found for session_key: " + lap.sessionKey())
                    );
        }
        if (lap.driverNumber() != null) {
            jpaDriverRepository.findByDriverNumber(lap.driverNumber())
                    .ifPresentOrElse(
                        entity::setDriver,
                        () -> LOGGER.warning("No DriverEntity found for driver_number: " + lap.driverNumber())
                    );
        }
        entity.setLapNumber(lap.lapNumber());
        entity.setLapDuration(lap.lapDuration());
        entity.setIOneSpeed(lap.iOneSpeed());
        entity.setITwoSpeed(lap.iTwoSpeed());
        entity.setStSpeed(lap.sTSpeed());
        entity.setSector1Duration(lap.sector1Duration());
        entity.setSector2Duration(lap.sector2Duration());
        entity.setSector3Duration(lap.sector3Duration());
        entity.setIsPitOutLap(lap.isPitOutLap());
        entity.setDateStart(lap.dateStart());
        return entity;
    }

    private Lap toDomain(LapEntity entity) {
        return new Lap(
                entity.getLapNumber(),
                entity.getSession().getSessionKey(),
                entity.getDriver().getDriverNumber(),
                entity.getIOneSpeed(),
                entity.getITwoSpeed(),
                entity.getStSpeed(),
                entity.getLapDuration(),
                entity.getSector1Duration(),
                entity.getSector2Duration(),
                entity.getSector3Duration(),
                entity.getIsPitOutLap(),
                entity.getDateStart()
        );
    }
}