package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.Meeting;

import java.util.List;

public interface MeetingRepositoryPort {
    void saveAll(List<Meeting> meetings);
    List<Meeting> findAll();
}