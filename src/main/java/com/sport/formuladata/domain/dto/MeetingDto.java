package com.sport.formuladata.domain.dto;

import java.time.ZonedDateTime;

public record MeetingDto(
        Integer meetingKey,
        String meetingName,
        String location,
        String countryName,
        String circuitShortName,
        ZonedDateTime dateStart,
        Integer year
) {
}