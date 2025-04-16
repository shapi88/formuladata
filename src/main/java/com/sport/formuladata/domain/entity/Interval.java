package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Interval(
        Session session,
        Driver driver,
        @JsonProperty("gap_to_leader") Float gapToLeader,
        @JsonProperty("interval") Float intervalToAhead,
        @JsonProperty("date") ZonedDateTime date
) {}