package com.sport.formuladata.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record Session(
        @JsonProperty("session_key") Integer sessionKey,
        Meeting meeting,
        @JsonProperty("session_type") String sessionType,
        @JsonProperty("session_name") String sessionName,
        @JsonProperty("date_start") LocalDateTime dateStart,
        @JsonProperty("date_end") LocalDateTime dateEnd
) {}