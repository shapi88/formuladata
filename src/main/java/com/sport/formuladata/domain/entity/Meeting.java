package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Meeting(
        @JsonProperty("meeting_key") Integer meetingKey,
        @JsonProperty("meeting_name") String meetingName,
        @JsonProperty("meeting_official_name") String meetingOfficialName,
        @JsonProperty("location") String location,
        @JsonProperty("country_key") Integer countryKey,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("country_name") String countryName,
        @JsonProperty("circuit_key") Integer circuitKey,
        @JsonProperty("circuit_short_name") String circuitShortName,
        @JsonProperty("gmt_offset") String gmtOffset,
        @JsonProperty("date_start") ZonedDateTime dateStart,
        @JsonProperty("year") Integer year
) {}