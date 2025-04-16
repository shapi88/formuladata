package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record Session(
        Integer sessionKey,
        Meeting meeting,
        String sessionType,
        String sessionName,
        LocalDateTime dateStart,
        LocalDateTime dateEnd
) {}