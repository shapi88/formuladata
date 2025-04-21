package com.sport.formuladata.application.service;

import com.sport.formuladata.domain.dto.SessionDto;
import com.sport.formuladata.domain.entity.*;
import com.sport.formuladata.domain.port.inbound.*;
import com.sport.formuladata.domain.port.outbound.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OpenF1Service implements FetchOpenF1DataUseCase, GetMeetingsUseCase, GetSessionsUseCase, GetDriversUseCase {
    private static final Logger LOGGER = Logger.getLogger(OpenF1Service.class.getName());
    private final OpenF1ApiPort openF1ApiPort;
    private final MeetingRepositoryPort meetingRepositoryPort;
    private final SessionRepositoryPort sessionRepositoryPort;
    private final DriverRepositoryPort driverRepositoryPort;
    private final LapRepositoryPort lapRepositoryPort;
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
        this.intervalRepositoryPort = intervalRepositoryPort;
        this.positionRepositoryPort = positionRepositoryPort;
    }

    @Override
    public void fetchAndStoreAllData() {
        this.fetchAndStoreMeetings();
        this.fetchAndStoreDrivers();
        this.fetchAndStoreSessions();
        this.fetchAndStoreIntervals();
        this.fetchAndStorePositions();
        this.fetchAndStoreLaps();
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
    public List<SessionDto> getAllSessionDtos() {
        return sessionRepositoryPort.findAllDtos();
    } 

    @Override
    public List<SessionDto> getSessionDtosByYearAndSessionName(Integer year, String session_name) {
        return sessionRepositoryPort.findByYearWithRelations(year, session_name);
    } 

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepositoryPort.findAll();
    }

    @Override
    public void fetchAndStoreMeetings() {
            List<Meeting> existingMeetings = meetingRepositoryPort.findAll();
            List<Meeting> newMeetings = openF1ApiPort.fetchMeetings().stream()
                    .filter(m -> existingMeetings.stream().noneMatch(em -> em.meetingKey().equals(m.meetingKey())))
                    .toList();
            LOGGER.info("Processing " + newMeetings.size() + " new meetings");
            meetingRepositoryPort.saveAll(newMeetings);
    }

    @Override
    public void fetchAndStoreSessions() {        
        List<Session> existingSessions = sessionRepositoryPort.findAll();
        List<Session> apiSessions = openF1ApiPort.fetchSessions();
        List<Session> newSessions = apiSessions.stream()
                .filter(s -> existingSessions.stream().noneMatch(es -> es.sessionKey().equals(s.sessionKey())))
                .map(s -> {
                    return new Session(
                            s.sessionKey(),
                            s.meetingKey(),
                            s.sessionType(),
                            s.sessionName(),
                            s.dateStart(),
                            s.dateEnd()
                    );
                })
                .toList();
        LOGGER.info("Processing " + newSessions.size() + " new sessions");
        sessionRepositoryPort.saveAll(newSessions);
    }

    @Override
    public void fetchAndStoreIntervals() {
        List<Session> existingSessions = sessionRepositoryPort.findAll();
        List<Interval> existingIntervals = intervalRepositoryPort.findAll();
        for (Session session : existingSessions) {
            if (session != null) {
                
                List<Interval> apiIntervals = openF1ApiPort.fetchIntervals(session.sessionKey());
                List<Interval> newIntervals = apiIntervals.stream()
                    .filter(i -> existingIntervals.stream().noneMatch(ei ->
                                ei.sessionKey() != null && i.sessionKey() != null &&
                                ei.driverNumber() != null && i.driverNumber() != null &&
                                ei.sessionKey().equals(i.sessionKey()) &&
                                ei.driverNumber().equals(i.driverNumber()) &&
                                ei.date().equals(i.date())))
                    .map(i -> {
                        return new Interval(
                            i.sessionKey(),
                            i.driverNumber(),
                            i.meetingKey(),
                            i.gapToLeader(),
                            i.intervalToAhead(),
                            i.date()
                            );
                        })
                    .toList();
                LOGGER.info("Processing " + newIntervals.size() + " new intervals for session " + session.sessionKey());
                intervalRepositoryPort.saveAll(newIntervals);
            }
        }
    }

    @Override
    public void fetchAndStoreDrivers() {
        List<Driver> existingDrivers = driverRepositoryPort.findAll();
        List<Driver> newDrivers = openF1ApiPort.fetchDrivers().stream()
                .filter(d -> existingDrivers.stream().noneMatch(ed -> ed.driverNumber().equals(d.driverNumber())))
                .toList();
        LOGGER.info("Processing " + newDrivers.size() + " new drivers");
        driverRepositoryPort.saveAll(newDrivers);
    }

    @Override
    public void fetchAndStorePositions() {
        List<Session> existingSessions = sessionRepositoryPort.findAll();
        for (Session existingSession : existingSessions) {
            Integer sessionKey = existingSession.sessionKey();
            List<Position> existingPositions = positionRepositoryPort.findAll();
            List<Position> apiPositions = openF1ApiPort.fetchPositions(sessionKey);
            List<Position> newPositions = apiPositions.stream().filter(p -> existingPositions.stream().noneMatch(ep ->
                            ep.sessionKey() != null && p.sessionKey() != null &&
                            ep.driverNumber() != null && p.driverNumber() != null &&
                            ep.sessionKey().equals(p.sessionKey()) &&
                            ep.driverNumber().equals(p.driverNumber()) &&
                            ep.date().equals(p.date())))
                    .map(p -> {
                        return new Position(
                                p.meetingKey(),
                                p.sessionKey(),
                                p.driverNumber(),
                                p.position(),
                                p.date()
                        );
                    })
                    .toList();
            LOGGER.info("Processing " + newPositions.size() + " new positions");
            positionRepositoryPort.saveAll(newPositions);
        }
    }

    @Override
    public void fetchAndStoreLaps() {
        List<Session> existingSessions = sessionRepositoryPort.findAll();
        List<Lap> existingLaps = lapRepositoryPort.findAll();
        for (Session existingSession : existingSessions) {
            Integer sessionKey = existingSession.sessionKey();
            List<Lap> apiLaps = openF1ApiPort.fetchLaps(sessionKey);
            List<Lap> newLaps = apiLaps.stream()
                    .filter(l -> existingLaps.stream().noneMatch(el ->
                            el.sessionKey() != null && l.sessionKey() != null &&
                            el.driverNumber() != null && l.driverNumber() != null &&
                            el.sessionKey().equals(l.sessionKey()) &&
                            el.driverNumber().equals(l.driverNumber()) &&
                            el.lapNumber().equals(l.lapNumber())))
                    .map(l -> new Lap(
                            l.lapNumber(),
                            l.sessionKey(),
                            l.driverNumber(),
                            l.iOneSpeed(),
                            l.iTwoSpeed(),
                            l.sTSpeed(),
                            l.lapDuration(),
                            l.sector1Duration(),
                            l.sector2Duration(),
                            l.sector3Duration(),
                            l.isPitOutLap(),
                            l.dateStart()
                    ))
                    .toList();
            LOGGER.info("Processing " + newLaps.size() + " new laps for session " + sessionKey);
            lapRepositoryPort.saveAll(newLaps);
        }
    }

}