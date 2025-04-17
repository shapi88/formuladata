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
        //this.fetchAndStoreMeetings();
        //this.fetchAndStoreDrivers();
        //this.fetchAndStoreSessions();
        //this.fetchAndStoreIntervals();
        //this.fetchAndStorePositions();
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
        // Drivers
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
        List<Driver> existingDrivers = driverRepositoryPort.findAll();
        for (Session existingSession : existingSessions) {
            Integer sessionKey = existingSession.sessionKey();
            List<Position> existingPositions = positionRepositoryPort.findAll();
            List<Position> apiPositions = openF1ApiPort.fetchPositions(sessionKey);
            //for (Driver existingDriver : existingDrivers) {
                //Integer driverNumber = existingDriver.driverNumber();
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
                continue;
            //}
        }
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

    // Positions
*/
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