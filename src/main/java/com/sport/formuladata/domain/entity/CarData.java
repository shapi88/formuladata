package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record CarData(
        Session session,
        Driver driver,
        @JsonProperty("date") ZonedDateTime date,
        @JsonProperty("rpm") Integer rpm,
        @JsonProperty("speed") Integer speed,
        @JsonProperty("n_gear") Integer gear,
        @JsonProperty("throttle") Float throttle,
        @JsonProperty("brake") Float brake,
        @JsonProperty("drs") Boolean drs
) {}