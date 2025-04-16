package com.sport.formuladata.domain.entity;

import java.time.LocalDateTime;

public record Meeting(
        Integer meetingKey,
        String meetingName,
        String location,
        String countryName,
        String circuitShortName,
        LocalDateTime dateStart
) {}