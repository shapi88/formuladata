package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record CarData(
        Integer carDataId,
        Session session,
        Driver driver,
        LocalDateTime date,
        Integer rpm,
        Integer speed,
        Integer gear,
        Float throttle,
        Float brake,
        Boolean drs,
        Float xPosition,
        Float yPosition,
        Float zPosition
) {}