package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.Session;

import java.util.List;

public interface GetSessionsUseCase {
    List<Session> getAllSessions();
}