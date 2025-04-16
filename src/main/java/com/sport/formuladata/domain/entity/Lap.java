package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record Lap(
        Integer lapId,
        Session session,
        Driver driver,
        Integer lapNumber,
        Float lapDuration,
        Float sector1Duration,
        Float sector2Duration,
        Float sector3Duration,
        Boolean isPitOutLap,
        LocalDateTime dateStart
) {}