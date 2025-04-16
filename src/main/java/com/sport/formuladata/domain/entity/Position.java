package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record Position(
        Integer positionId,
        Session session,
        Driver driver,
        Integer position,
        LocalDateTime date
) {}