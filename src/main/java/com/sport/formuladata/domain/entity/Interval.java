package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Interval(
        @JsonProperty("session_key") Integer sessionKey,
        @JsonProperty("driver_number") Integer driverNumber,
        @JsonProperty("meeting_key") Integer meetingKey,
        @JsonProperty("gap_to_leader") Float gapToLeader,
        @JsonProperty("interval") Float intervalToAhead,
        @JsonProperty("date") ZonedDateTime date
) {}