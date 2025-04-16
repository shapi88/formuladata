package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.*;

import java.util.List;

public interface OpenF1ApiPort {
    List<Meeting> fetchMeetings();
    List<Session> fetchSessions();
    List<Driver> fetchDrivers();
    List<Lap> fetchLaps(Integer sessionKey, Integer driverNumber);
    List<CarData> fetchCarData(Integer sessionKey, Integer driverNumber);
    List<Interval> fetchIntervals(Integer sessionKey);
    List<Position> fetchPositions();
}