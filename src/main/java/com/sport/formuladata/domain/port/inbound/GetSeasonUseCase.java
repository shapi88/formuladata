package com.sport.formuladata.domain.port.inbound;

import java.util.List;

import com.sport.formuladata.domain.dto.SessionDto;
import com.sport.formuladata.domain.entity.Session;

public interface GetSeasonUseCase {
    List<Session> getAllSeasons();
    List<SessionDto> getAllSeasonDto();
}
