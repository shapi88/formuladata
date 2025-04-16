package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Meeting;
import com.sport.formuladata.domain.port.outbound.MeetingRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.MeetingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingRepositoryAdapter implements MeetingRepositoryPort {
    private final JpaMeetingRepository jpaRepository;

    public MeetingRepositoryAdapter(JpaMeetingRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<Meeting> meetings) {
        List<MeetingEntity> entities = meetings.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Meeting> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private MeetingEntity toEntity(Meeting meeting) {
        MeetingEntity entity = new MeetingEntity();
        entity.setMeetingKey(meeting.meetingKey());
        entity.setMeetingName(meeting.meetingName());
        entity.setLocation(meeting.location());
        entity.setCountryName(meeting.countryName());
        entity.setCircuitShortName(meeting.circuitShortName());
        entity.setDateStart(meeting.dateStart());
        return entity;
    }

    private Meeting toDomain(MeetingEntity entity) {
        return new Meeting(
                entity.getMeetingKey(),
                entity.getMeetingName(),
                entity.getLocation(),
                entity.getCountryName(),
                entity.getCircuitShortName(),
                entity.getDateStart()
        );
    }
}