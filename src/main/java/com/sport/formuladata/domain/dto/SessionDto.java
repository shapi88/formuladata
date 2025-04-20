package com.sport.formuladata.domain.dto;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.MeetingEntity;

import java.time.ZonedDateTime;

public record SessionDto(
        Integer sessionKey,
        MeetingEntity meeting,
        String sessionType,
        String sessionName,
        ZonedDateTime dateStart,
        ZonedDateTime dateEnd
) {}