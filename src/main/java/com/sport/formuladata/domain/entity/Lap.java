package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Lap(
        @JsonProperty("lap_number") Integer lapNumber,
        Session session,
        Driver driver,
        @JsonProperty("duration") Float lapDuration,
        @JsonProperty("sector_1") Float sector1Duration,
        @JsonProperty("sector_2") Float sector2Duration,
        @JsonProperty("sector_3") Float sector3Duration,
        @JsonProperty("is_pit_out_lap") Boolean isPitOutLap,
        @JsonProperty("date_start") ZonedDateTime dateStart
) {}