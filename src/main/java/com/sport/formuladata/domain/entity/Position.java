package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Position(
        @JsonProperty("position_id") Integer positionId,
        Session session,
        Driver driver,
        @JsonProperty("position") Integer position,
        @JsonProperty("date") LocalDateTime date
) {}