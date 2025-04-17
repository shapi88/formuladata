package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public record Position(
        @JsonProperty("meeting_key") Integer meetingKey,
        @JsonProperty("session_key") Integer sessionKey,
        @JsonProperty("driver_number") Integer driverNumber,
        @JsonProperty("position") Integer position,
        @JsonProperty("date") ZonedDateTime date
) {}