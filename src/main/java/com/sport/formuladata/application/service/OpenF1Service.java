package com.sport.formuladata.application.service;

import com.sport.formuladata.domain.entity.*;
import com.sport.formuladata.domain.port.inbound.*;
import com.sport.formuladata.domain.port.outbound.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OpenF1Service implements FetchOpenF1DataUseCase, GetMeetingsUseCase, GetSessionsUseCase, GetDriversUseCase, GetCarDataUseCase, GetLapsUseCase, GetPositionsUseCase {
    private static final Logger LOGGER = Logger.getLogger(OpenF1Service.class.getName());
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
        this.fetchAndStoreMeetings();
        this.fetchAndStoreDrivers();
        this.fetchAndStoreSessions();
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

    @Override
    public void fetchAndStoreMeetings() {
            // Meetings
            List<Meeting> existingMeetings = meetingRepositoryPort.findAll();
            List<Meeting> newMeetings = openF1ApiPort.fetchMeetings().stream()
                    .filter(m -> existingMeetings.stream().noneMatch(em -> em.meetingKey().equals(m.meetingKey())))
                    .toList();
            LOGGER.info("Processing " + newMeetings.size() + " new meetings");
            meetingRepositoryPort.saveAll(newMeetings);
    }

    @Override
    public void fetchAndStoreSessions() {
        
        // Sessions
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
    public void fetchAndStoreDrivers() {
        // Drivers
        List<Driver> existingDrivers = driverRepositoryPort.findAll();
        List<Driver> newDrivers = openF1ApiPort.fetchDrivers().stream()
                .filter(d -> existingDrivers.stream().noneMatch(ed -> ed.driverNumber().equals(d.driverNumber())))
                .toList();
        LOGGER.info("Processing " + newDrivers.size() + " new drivers");
        driverRepositoryPort.saveAll(newDrivers);
}

    @Override
    public void fetch() {
        /*
       // Fetch Laps, CarData, Intervals for each new session and driver
       for (Session session : newSessions) {
        Integer sessionKey = session.sessionKey();
        for (Driver driver : newDrivers) {
            Integer driverNumber = driver.driverNumber();

            // Laps
            List<Lap> existingLaps = lapRepositoryPort.findAll();
            List<Lap> apiLaps = openF1ApiPort.fetchLaps(sessionKey, driverNumber);
            List<Lap> newLaps = apiLaps.stream()
                    .filter(l -> existingLaps.stream().noneMatch(el ->
                            el.session() != null && l.session() != null &&
                            el.driver() != null && l.driver() != null &&
                            el.session().sessionKey().equals(l.session().sessionKey()) &&
                            el.driver().driverNumber().equals(l.driver().driverNumber()) &&
                            el.lapNumber().equals(l.lapNumber())))
                    .map(l -> new Lap(
                            l.lapNumber(),
                            session,
                            driver,
                            l.lapDuration(),
                            l.sector1Duration(),
                            l.sector2Duration(),
                            l.sector3Duration(),
                            l.isPitOutLap(),
                            l.dateStart()
                    ))
                    .toList();
            LOGGER.info("Processing " + newLaps.size() + " new laps for session " + sessionKey + ", driver " + driverNumber);
            lapRepositoryPort.saveAll(newLaps);

            // Car Data
            List<CarData> existingCarData = carDataRepositoryPort.findAll();
            List<CarData> apiCarData = openF1ApiPort.fetchCarData(sessionKey, driverNumber);
            List<CarData> newCarData = apiCarData.stream()
                    .filter(cd -> existingCarData.stream().noneMatch(ecd ->
                            ecd.session() != null && cd.session() != null &&
                            ecd.driver() != null && cd.driver() != null &&
                            ecd.session().sessionKey().equals(cd.session().sessionKey()) &&
                            ecd.driver().driverNumber().equals(cd.driver().driverNumber()) &&
                            ecd.date().equals(cd.date())))
                    .map(cd -> new CarData(
                            session,
                            driver,
                            cd.date(),
                            cd.rpm(),
                            cd.speed(),
                            cd.gear(),
                            cd.throttle(),
                            cd.brake(),
                            cd.drs()
                    ))
                    .toList();
            LOGGER.info("Processing " + newCarData.size() + " new car data entries for session " + sessionKey + ", driver " + driverNumber);
            carDataRepositoryPort.saveAll(newCarData);
        }

        // Intervals
        List<Interval> existingIntervals = intervalRepositoryPort.findAll();
        List<Interval> apiIntervals = openF1ApiPort.fetchIntervals(sessionKey);
        List<Interval> newIntervals = apiIntervals.stream()
                .filter(i -> existingIntervals.stream().noneMatch(ei ->
                        ei.session() != null && i.session() != null &&
                        ei.driver() != null && i.driver() != null &&
                        ei.session().sessionKey().equals(i.session().sessionKey()) &&
                        ei.driver().driverNumber().equals(i.driver().driverNumber()) &&
                        ei.date().equals(i.date())))
                .map(i -> {
                    Driver intervalDriver = newDrivers.stream()
                            .filter(d -> d.driverNumber().equals(i.driver().driverNumber()))
                            .findFirst()
                            .orElseGet(() -> existingDrivers.stream()
                                    .filter(d -> d.driverNumber().equals(i.driver().driverNumber()))
                                    .findFirst()
                                    .orElse(null));
                    return new Interval(
                            session,
                            intervalDriver,
                            i.gapToLeader(),
                            i.intervalToAhead(),
                            i.date()
                    );
                })
                .toList();
        LOGGER.info("Processing " + newIntervals.size() + " new intervals for session " + sessionKey);
        intervalRepositoryPort.saveAll(newIntervals);
    }

    // Positions
    List<Position> existingPositions = positionRepositoryPort.findAll();
    List<Position> newPositions = openF1ApiPort.fetchPositions().stream()
            .filter(p -> existingPositions.stream().noneMatch(ep ->
                    ep.session() != null && p.session() != null &&
                    ep.driver() != null && p.driver() != null &&
                    ep.session().sessionKey().equals(p.session().sessionKey()) &&
                    ep.driver().driverNumber().equals(p.driver().driverNumber()) &&
                    ep.date().equals(p.date())))
            .map(p -> {
                Session session = newSessions.stream()
                        .filter(s -> s.sessionKey().equals(p.session() != null ? p.session().sessionKey() : null))
                        .findFirst()
                        .orElseGet(() -> existingSessions.stream()
                                .filter(s -> s.sessionKey().equals(p.session() != null ? p.session().sessionKey() : null))
                                .findFirst()
                                .orElse(null));
                Driver driver = newDrivers.stream()
                        .filter(d -> d.driverNumber().equals(p.driver() != null ? p.driver().driverNumber() : null))
                        .findFirst()
                        .orElseGet(() -> existingDrivers.stream()
                                .filter(d -> d.driverNumber().equals(p.driver() != null ? p.driver().driverNumber() : null))
                                .findFirst()
                                .orElse(null));
                return new Position(
                        p.positionId(),
                        session,
                        driver,
                        p.position(),
                        p.date()
                );
            })
            .toList();
    LOGGER.info("Processing " + newPositions.size() + " new positions");
    positionRepositoryPort.saveAll(newPositions);*/
        throw new UnsupportedOperationException("Unimplemented method 'getAllPositions'");
    }

    @Override
    public List<Position> getAllPositions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPositions'");
    }

    @Override
    public List<Lap> getAllLaps() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllLaps'");
    }

    @Override
    public List<CarData> getAllCarData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCarData'");
    }
}