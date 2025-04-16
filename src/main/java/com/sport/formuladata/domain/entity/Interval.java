package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Interval(
        @JsonProperty("interval_id") Integer intervalId,
        Session session,
        Driver driver,
        @JsonProperty("gap_to_leader") Float gapToLeader,
        @JsonProperty("interval_to_ahead") Float intervalToAhead,
        @JsonProperty("date") LocalDateTime date
) {}