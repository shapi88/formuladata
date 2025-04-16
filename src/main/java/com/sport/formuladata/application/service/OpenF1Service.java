package com.sport.formuladata.application.service;

import com.sport.formuladata.domain.entity.*;
import com.sport.formuladata.domain.port.inbound.*;
import com.sport.formuladata.domain.port.outbound.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenF1Service implements FetchOpenF1DataUseCase, GetMeetingsUseCase, GetSessionsUseCase, GetDriversUseCase {
    private final OpenF1ApiPort openF1ApiPort;
    private final MeetingRepositoryPort meetingRepositoryPort;
    private final SessionRepositoryPort sessionRepositoryPort;
    private final DriverRepositoryPort driverRepositoryPort;
    private final LapRepositoryPort lapRepositoryPort;
    private final CarDataRepositoryPort carDataRepositoryPort;
    private final IntervalRepositoryPort intervalRepositoryPort;
    private final PositionRepositoryPort positionRepositoryPort;

    public OpenF1Service(
            OpenF1ApiPort openF1ApiPort,
            MeetingRepositoryPort meetingRepositoryPort,
            SessionRepositoryPort sessionRepositoryPort,
            DriverRepositoryPort driverRepositoryPort,
            LapRepositoryPort lapRepositoryPort,
            CarDataRepositoryPort carDataRepositoryPort,
            IntervalRepositoryPort intervalRepositoryPort,
            PositionRepositoryPort positionRepositoryPort
    ) {
        this.openF1ApiPort = openF1ApiPort;
        this.meetingRepositoryPort = meetingRepositoryPort;
        this.sessionRepositoryPort = sessionRepositoryPort;
        this.driverRepositoryPort = driverRepositoryPort;
        this.lapRepositoryPort = lapRepositoryPort;
        this.carDataRepositoryPort = carDataRepositoryPort;
        this.intervalRepositoryPort = intervalRepositoryPort;
        this.positionRepositoryPort = positionRepositoryPort;
    }

    @Override
    public void fetchAndStoreAllData() {
        List<Meeting> meetings = openF1ApiPort.fetchMeetings();
        meetingRepositoryPort.saveAll(meetings);

        List<Session> sessions = openF1ApiPort.fetchSessions();
        sessionRepositoryPort.saveAll(sessions);

        List<Driver> drivers = openF1ApiPort.fetchDrivers();
        driverRepositoryPort.saveAll(drivers);

        List<Lap> laps = openF1ApiPort.fetchLaps();
        lapRepositoryPort.saveAll(laps);

        List<CarData> carData = openF1ApiPort.fetchCarData();
        carDataRepositoryPort.saveAll(carData);

        List<Interval> intervals = openF1ApiPort.fetchIntervals();
        intervalRepositoryPort.saveAll(intervals);

        List<Position> positions = openF1ApiPort.fetchPositions();
        positionRepositoryPort.saveAll(positions);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepositoryPort.findAll();
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepositoryPort.findAll();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepositoryPort.findAll();
    }
}