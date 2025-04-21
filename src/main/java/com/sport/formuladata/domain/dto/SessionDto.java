package com.sport.formuladata.domain.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record SessionDto(
        Integer sessionKey,
        MeetingDto meeting,
        String sessionType,
        String sessionName,
        ZonedDateTime dateStart,
        ZonedDateTime dateEnd,
        List<LapDto> laps
) {}