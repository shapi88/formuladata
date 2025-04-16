package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record CarData(
        @JsonProperty("car_data_id") Integer carDataId,
        Session session,
        Driver driver,
        @JsonProperty("date") LocalDateTime date,
        @JsonProperty("rpm") Integer rpm,
        @JsonProperty("speed") Integer speed,
        @JsonProperty("gear") Integer gear,
        @JsonProperty("throttle") Float throttle,
        @JsonProperty("brake") Float brake,
        @JsonProperty("drs") Boolean drs,
        @JsonProperty("x_position") Float xPosition,
        @JsonProperty("y_position") Float yPosition,
        @JsonProperty("z_position") Float zPosition
) {}