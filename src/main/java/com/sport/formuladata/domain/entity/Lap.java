package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Lap(
        @JsonProperty("lap_id") Integer lapId,
        Session session,
        Driver driver,
        @JsonProperty("lap_number") Integer lapNumber,
        @JsonProperty("lap_duration") Float lapDuration,
        @JsonProperty("sector_1_duration") Float sector1Duration,
        @JsonProperty("sector_2_duration") Float sector2Duration,
        @JsonProperty("sector_3_duration") Float sector3Duration,
        @JsonProperty("is_pit_out_lap") Boolean isPitOutLap,
        @JsonProperty("date_start") LocalDateTime dateStart
) {}