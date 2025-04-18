package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Lap(
        @JsonProperty("lap_number") Integer lapNumber,
        @JsonProperty("session_key") Integer sessionKey,
        @JsonProperty("driver_number") Integer driverNumber,
        @JsonProperty("lap_duration") Float lapDuration,
        @JsonProperty("i1_speed") Float iOneSpeed,
        @JsonProperty("i2_speed") Float iTwoSpeed,
        @JsonProperty("st_speed") Float sTSpeed,
        @JsonProperty("duration_sector_1") Float sector1Duration,
        @JsonProperty("duration_sector_2") Float sector2Duration,
        @JsonProperty("duration_sector_3") Float sector3Duration,
        @JsonProperty("is_pit_out_lap") Boolean isPitOutLap,
        @JsonProperty("date_start") ZonedDateTime dateStart
) {}