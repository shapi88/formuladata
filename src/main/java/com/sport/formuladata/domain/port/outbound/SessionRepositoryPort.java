package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.dto.SessionDto;
import com.sport.formuladata.domain.entity.Session;

import java.util.List;

public interface SessionRepositoryPort {
    void saveAll(List<Session> sessions);
    List<Session> findAll();
    List<SessionDto> findAllDtos();
}