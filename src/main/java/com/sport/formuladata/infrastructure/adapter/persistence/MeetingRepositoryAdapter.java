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
        entity.setMeetingOfficialName(meeting.meetingOfficialName());
        entity.setLocation(meeting.location());
        entity.setCountryKey(meeting.countryKey());
        entity.setCountryCode(meeting.countryCode());
        entity.setCountryName(meeting.countryName());
        entity.setCircuitKey(meeting.circuitKey());
        entity.setCircuitShortName(meeting.circuitShortName());
        entity.setGmtOffset(meeting.gmtOffset());
        entity.setDateStart(meeting.dateStart());
        entity.setYear(meeting.year());
        return entity;
    }

    private Meeting toDomain(MeetingEntity entity) {
        return new Meeting(
                entity.getMeetingKey(),
                entity.getMeetingName(),
                entity.getMeetingOfficialName(),
                entity.getLocation(),
                entity.getCountryKey(),
                entity.getCountryCode(),
                entity.getCountryName(),
                entity.getCircuitKey(),
                entity.getCircuitShortName(),
                entity.getGmtOffset(),
                entity.getDateStart(),
                entity.getYear()
        );
    }
}