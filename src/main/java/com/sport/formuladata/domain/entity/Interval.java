package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record Interval(
        Integer intervalId,
        Session session,
        Driver driver,
        Float gapToLeader,
        Float intervalToAhead,
        LocalDateTime date
) {}