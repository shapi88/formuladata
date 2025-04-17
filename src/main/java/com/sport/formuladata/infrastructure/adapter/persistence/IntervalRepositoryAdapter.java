package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Interval;
import com.sport.formuladata.domain.port.outbound.IntervalRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.IntervalEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class IntervalRepositoryAdapter implements IntervalRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(IntervalRepositoryAdapter.class.getName());
    private final JpaSessionRepository jpaSessionRepository;
    private final JpaIntervalRepository jpaRepository;
    private final JpaMeetingRepository jpaMeetingRepository;
    private final JpaDriverRepository jpaDriverRepository;

    public IntervalRepositoryAdapter(
            JpaIntervalRepository jpaRepository,
            JpaMeetingRepository jpaMeetingRepository,
            JpaDriverRepository jpaDriverRepository,
            JpaSessionRepository jpaSessionRepository
        ) {
        this.jpaRepository = jpaRepository;
        this.jpaMeetingRepository = jpaMeetingRepository;
        this.jpaDriverRepository = jpaDriverRepository;
        this.jpaSessionRepository = jpaSessionRepository;
    }

    @Override
    public void saveAll(List<Interval> intervals) {
        LOGGER.info("Saving " + intervals.size() + " interval entries");
        List<IntervalEntity> entities = intervals.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Interval> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private IntervalEntity toEntity(Interval interval) {
        IntervalEntity entity = new IntervalEntity();
        if (interval.sessionKey() != null) {
            jpaSessionRepository.findById(interval.sessionKey())
                    .ifPresentOrElse(
                        entity::setSession,
                        () -> LOGGER.warning("No Session Entity found for session_key: " + interval.sessionKey())
                    );
        }
        if (interval.meetingKey() != null) {
            jpaMeetingRepository.findById(interval.meetingKey())
                    .ifPresentOrElse(
                            entity::setMeeting,
                            () -> LOGGER.warning("No MeetingEntity found for meeting_key: " + interval.meetingKey())
                    );
        }
        if (interval.driverNumber() != null) {
            jpaDriverRepository.findByDriverNumber(interval.driverNumber())
                    .ifPresentOrElse(
                        entity::setDriver,
                        () -> LOGGER.warning("No DriverEntity found for driver_number: " + interval.driverNumber())
                    );
        }
        entity.setGapToLeader(interval.gapToLeader());
        entity.setIntervalToAhead(interval.intervalToAhead());
        entity.setDate(interval.date());
        return entity;
    }

    private Interval toDomain(IntervalEntity entity) {
        return new Interval(
                entity.getSession().getSessionKey(),
                entity.getDriver().getDriverNumber(),
                entity.getMeeting().getMeetingKey(),
                entity.getGapToLeader(),
                entity.getIntervalToAhead(),
                entity.getDate()
        );
    }
}