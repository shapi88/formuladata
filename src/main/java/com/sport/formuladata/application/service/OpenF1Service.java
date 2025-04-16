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
        // Meetings
        List<Meeting> existingMeetings = meetingRepositoryPort.findAll();
        List<Meeting> newMeetings = openF1ApiPort.fetchMeetings().stream()
                .filter(m -> existingMeetings.stream().noneMatch(em -> em.meetingKey().equals(m.meetingKey())))
                .toList();
        meetingRepositoryPort.saveAll(newMeetings);

        // Sessions
        List<Session> existingSessions = sessionRepositoryPort.findAll();
        List<Session> newSessions = openF1ApiPort.fetchSessions().stream()
                .filter(s -> existingSessions.stream().noneMatch(es -> es.sessionKey().equals(s.sessionKey())))
                .toList();
        sessionRepositoryPort.saveAll(newSessions);

        // Drivers
        List<Driver> existingDrivers = driverRepositoryPort.findAll();
        List<Driver> newDrivers = openF1ApiPort.fetchDrivers().stream()
                .filter(d -> existingDrivers.stream().noneMatch(ed -> ed.driverNumber().equals(d.driverNumber())))
                .toList();
        driverRepositoryPort.saveAll(newDrivers);

        // Laps
        List<Lap> existingLaps = lapRepositoryPort.findAll();
        List<Lap> newLaps = openF1ApiPort.fetchLaps().stream()
                .filter(l -> existingLaps.stream().noneMatch(el ->
                        el.session() != null && l.session() != null &&
                        el.driver() != null && l.driver() != null &&
                        el.session().sessionKey().equals(l.session().sessionKey()) &&
                        el.driver().driverNumber().equals(l.driver().driverNumber()) &&
                        el.lapNumber().equals(l.lapNumber())))
                .toList();
        lapRepositoryPort.saveAll(newLaps);

        // Car Data
        List<CarData> existingCarData = carDataRepositoryPort.findAll();
        List<CarData> newCarData = openF1ApiPort.fetchCarData().stream()
                .filter(cd -> existingCarData.stream().noneMatch(ecd ->
                        ecd.session() != null && cd.session() != null &&
                        ecd.driver() != null && cd.driver() != null &&
                        ecd.session().sessionKey().equals(cd.session().sessionKey()) &&
                        ecd.driver().driverNumber().equals(cd.driver().driverNumber()) &&
                        ecd.date().equals(cd.date())))
                .toList();
        carDataRepositoryPort.saveAll(newCarData);

        // Intervals
        List<Interval> existingIntervals = intervalRepositoryPort.findAll();
        List<Interval> newIntervals = openF1ApiPort.fetchIntervals().stream()
                .filter(i -> existingIntervals.stream().noneMatch(ei ->
                        ei.session() != null && i.session() != null &&
                        ei.driver() != null && i.driver() != null &&
                        ei.session().sessionKey().equals(i.session().sessionKey()) &&
                        ei.driver().driverNumber().equals(i.driver().driverNumber()) &&
                        ei.date().equals(i.date())))
                .toList();
        intervalRepositoryPort.saveAll(newIntervals);

        // Positions
        List<Position> existingPositions = positionRepositoryPort.findAll();
        List<Position> newPositions = openF1ApiPort.fetchPositions().stream()
                .filter(p -> existingPositions.stream().noneMatch(ep ->
                        ep.session() != null && p.session() != null &&
                        ep.driver() != null && p.driver() != null &&
                        ep.session().sessionKey().equals(p.session().sessionKey()) &&
                        ep.driver().driverNumber().equals(p.driver().driverNumber()) &&
                        ep.date().equals(p.date())))
                .toList();
        positionRepositoryPort.saveAll(newPositions);
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