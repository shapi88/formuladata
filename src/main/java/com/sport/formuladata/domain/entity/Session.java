package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Session(
        @JsonProperty("session_key") Integer sessionKey,
        @JsonProperty("meeting_key") Integer meetingKey,
        //Meeting meeting,
        @JsonProperty("session_type") String sessionType,
        @JsonProperty("session_name") String sessionName,
        @JsonProperty("date_start") ZonedDateTime dateStart,
        @JsonProperty("date_end") ZonedDateTime dateEnd
) {}