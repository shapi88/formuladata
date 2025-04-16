package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.*;

import java.util.List;

public interface OpenF1ApiPort {
    List<Meeting> fetchMeetings();
    List<Session> fetchSessions();
    List<Driver> fetchDrivers();
    List<Lap> fetchLaps();
    List<CarData> fetchCarData();
    List<Interval> fetchIntervals();
    List<Position> fetchPositions();
}