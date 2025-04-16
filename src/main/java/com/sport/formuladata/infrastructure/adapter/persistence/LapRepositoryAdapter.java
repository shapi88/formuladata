package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Driver;
import com.sport.formuladata.domain.entity.Lap;
import com.sport.formuladata.domain.entity.Session;
import com.sport.formuladata.domain.port.outbound.LapRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.LapEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LapRepositoryAdapter implements LapRepositoryPort {
    private final JpaLapRepository jpaRepository;

    public LapRepositoryAdapter(JpaLapRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<Lap> laps) {
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
        entity.setLapId(lap.lapId());
        if (lap.session() != null) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionKey(lap.session().sessionKey());
            entity.setSession(sessionEntity);
        }
        if (lap.driver() != null) {
            DriverEntity driverEntity = new DriverEntity();
            driverEntity.setDriverNumber(lap.driver().driverNumber());
            entity.setDriver(driverEntity);
        }
        entity.setLapNumber(lap.lapNumber());
        entity.setLapDuration(lap.lapDuration());
        entity.setSector1Duration(lap.sector1Duration());
        entity.setSector2Duration(lap.sector2Duration());
        entity.setSector3Duration(lap.sector3Duration());
        entity.setIsPitOutLap(lap.isPitOutLap());
        entity.setDateStart(lap.dateStart());
        return entity;
    }

    private Lap toDomain(LapEntity entity) {
        Session session = entity.getSession() != null
                ? new Session(entity.getSession().getSessionKey(), null, null, null, null, null)
                : null;
        Driver driver = entity.getDriver() != null
                ? new Driver(entity.getDriver().getDriverNumber(), null, null, null, null)
                : null;
        return new Lap(
                entity.getLapId(),
                session,
                driver,
                entity.getLapNumber(),
                entity.getLapDuration(),
                entity.getSector1Duration(),
                entity.getSector2Duration(),
                entity.getSector3Duration(),
                entity.getIsPitOutLap(),
                entity.getDateStart()
        );
    }
}