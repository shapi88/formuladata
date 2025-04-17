package com.sport.formuladata.infrastructure.adapter.api;

import com.sport.formuladata.domain.entity.*;
import com.sport.formuladata.domain.port.outbound.OpenF1ApiPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class OpenF1ApiAdapter implements OpenF1ApiPort {
    private static final Logger LOGGER = Logger.getLogger(OpenF1ApiAdapter.class.getName());
    private final RestClient openF1RestClient;

    public OpenF1ApiAdapter(RestClient openF1RestClient) {
        this.openF1RestClient = openF1RestClient;
    }

    @Override
    public List<Meeting> fetchMeetings() {
        try {
            Meeting[] meetings = openF1RestClient.get()
                    .uri("/meetings")
                    .retrieve()
                    .body(Meeting[].class);
            LOGGER.info("Fetched " + (meetings != null ? meetings.length : 0) + " meetings");
            return meetings != null ? Arrays.asList(meetings) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch meetings: " + e.getMessage() + " - Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "Unknown"));
            return Collections.emptyList();
        }
    }

    @Override
    public List<Session> fetchSessions() {
        try {
            Session[] sessions = openF1RestClient.get()
                    .uri("/sessions")
                    .retrieve()
                    .body(Session[].class);
            LOGGER.info("Fetched " + (sessions != null ? sessions.length : 0) + " sessions");
            return sessions != null ? Arrays.asList(sessions) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch sessions: " + e.getMessage() + " - Cause: " + (e.getCause() != null ? e.getCause().getMessage() : "Unknown"));
            return Collections.emptyList();
        }
    }

    @Override
    public List<Driver> fetchDrivers() {
        try {
            Driver[] drivers = openF1RestClient.get()
                    .uri("/drivers")
                    .retrieve()
                    .body(Driver[].class);
            LOGGER.info("Fetched " + (drivers != null ? drivers.length : 0) + " drivers");
            return drivers != null ? Arrays.asList(drivers) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch drivers: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Lap> fetchLaps(Integer sessionKey, Integer driverNumber) {
        try {
            Lap[] laps = openF1RestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/laps")
                            .queryParam("session_key", sessionKey)
                            .queryParam("driver_number", driverNumber)
                            .build())
                    .retrieve()
                    .body(Lap[].class);
            LOGGER.info("Fetched " + (laps != null ? laps.length : 0) + " laps for session " + sessionKey + ", driver " + driverNumber);
            return laps != null ? Arrays.asList(laps) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch laps: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CarData> fetchCarData(Integer sessionKey, Integer driverNumber) {
        try {
            CarData[] carData = openF1RestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/car_data")
                            .queryParam("session_key", sessionKey)
                            .queryParam("driver_number", driverNumber)
                            .build())
                    .retrieve()
                    .body(CarData[].class);
            LOGGER.info("Fetched " + (carData != null ? carData.length : 0) + " car data entries for session " + sessionKey + ", driver " + driverNumber);
            return carData != null ? Arrays.asList(carData) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch car data: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Interval> fetchIntervals(Integer sessionKey) {
        try {
            Interval[] intervals = openF1RestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/intervals")
                            .queryParam("session_key", sessionKey)
                            .build())
                    .retrieve()
                    .body(Interval[].class);
            LOGGER.info("Fetched " + (intervals != null ? intervals.length : 0) + " intervals for session " + sessionKey);
            return intervals != null ? Arrays.asList(intervals) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch intervals: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Position> fetchPositions(Integer sessionKey) {
        try {
            Position[] positions = openF1RestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/position")
                            .queryParam("session_key", sessionKey)
                            .build())
                    .retrieve()
                    .body(Position[].class);
            LOGGER.info("Fetched " + (positions != null ? positions.length : 0) + " positions for session " + sessionKey);
            return positions != null ? Arrays.asList(positions) : Collections.emptyList();
        } catch (RestClientException e) {
            LOGGER.severe("Failed to fetch positions: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}