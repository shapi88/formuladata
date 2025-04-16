package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Driver;
import com.sport.formuladata.domain.entity.Interval;
import com.sport.formuladata.domain.entity.Session;
import com.sport.formuladata.domain.port.outbound.IntervalRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.IntervalEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IntervalRepositoryAdapter implements IntervalRepositoryPort {
    private final JpaIntervalRepository jpaRepository;

    public IntervalRepositoryAdapter(JpaIntervalRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<Interval> intervals) {
        List<IntervalEntity> entities = intervals.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    private IntervalEntity toEntity(Interval interval) {
        IntervalEntity entity = new IntervalEntity();
        entity.setIntervalId(interval.intervalId());
        if (interval.session() != null) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionKey(interval.session().sessionKey());
            entity.setSession(sessionEntity);
        }
        if (interval.driver() != null) {
            DriverEntity driverEntity = new DriverEntity();
            driverEntity.setDriverNumber(interval.driver().driverNumber());
            entity.setDriver(driverEntity);
        }
        entity.setGapToLeader(interval.gapToLeader());
        entity.setIntervalToAhead(interval.intervalToAhead());
        entity.setDate(interval.date());
        return entity;
    }
}