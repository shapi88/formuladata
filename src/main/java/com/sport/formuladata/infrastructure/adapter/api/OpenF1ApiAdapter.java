package com.sport.formuladata.infrastructure.adapter.api;

import com.sport.formuladata.domain.entity.*;
import com.sport.formuladata.domain.port.outbound.OpenF1ApiPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class OpenF1ApiAdapter implements OpenF1ApiPort {
    private final RestClient openF1RestClient;

    public OpenF1ApiAdapter(RestClient openF1RestClient) {
        this.openF1RestClient = openF1RestClient;
    }

    @Override
    public List<Meeting> fetchMeetings() {
        Meeting[] meetings = openF1RestClient.get()
                .uri("/meetings")
                .retrieve()
                .body(Meeting[].class);
        return meetings != null ? Arrays.asList(meetings) : Collections.emptyList();
    }

    @Override
    public List<Session> fetchSessions() {
        Session[] sessions = openF1RestClient.get()
                .uri("/sessions")
                .retrieve()
                .body(Session[].class);
        return sessions != null ? Arrays.asList(sessions) : Collections.emptyList();
    }

    @Override
    public List<Driver> fetchDrivers() {
        Driver[] drivers = openF1RestClient.get()
                .uri("/drivers")
                .retrieve()
                .body(Driver[].class);
        return drivers != null ? Arrays.asList(drivers) : Collections.emptyList();
    }

    @Override
    public List<Lap> fetchLaps() {
        Lap[] laps = openF1RestClient.get()
                .uri("/laps")
                .retrieve()
                .body(Lap[].class);
        return laps != null ? Arrays.asList(laps) : Collections.emptyList();
    }

    @Override
    public List<CarData> fetchCarData() {
        CarData[] carData = openF1RestClient.get()
                .uri("/car_data")
                .retrieve()
                .body(CarData[].class);
        return carData != null ? Arrays.asList(carData) : Collections.emptyList();
    }

    @Override
    public List<Interval> fetchIntervals() {
        Interval[] intervals = openF1RestClient.get()
                .uri("/intervals")
                .retrieve()
                .body(Interval[].class);
        return intervals != null ? Arrays.asList(intervals) : Collections.emptyList();
    }

    @Override
    public List<Position> fetchPositions() {
        Position[] positions = openF1RestClient.get()
                .uri("/position")
                .retrieve()
                .body(Position[].class);
        return positions != null ? Arrays.asList(positions) : Collections.emptyList();
    }
}