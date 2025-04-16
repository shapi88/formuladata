package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Meeting(
        @JsonProperty("meeting_key") Integer meetingKey,
        @JsonProperty("meeting_name") String meetingName,
        @JsonProperty("location") String location,
        @JsonProperty("country_name") String countryName,
        @JsonProperty("circuit_short_name") String circuitShortName,
        @JsonProperty("date_start") LocalDateTime dateStart
) {}