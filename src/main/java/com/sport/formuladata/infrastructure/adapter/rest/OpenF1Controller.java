package com.sport.formuladata.infrastructure.adapter.rest;

import com.sport.formuladata.domain.dto.SessionDto;
import com.sport.formuladata.domain.entity.Driver;
import com.sport.formuladata.domain.entity.Meeting;
import com.sport.formuladata.domain.entity.Session;
import com.sport.formuladata.domain.port.inbound.FetchOpenF1DataUseCase;
import com.sport.formuladata.domain.port.inbound.GetDriversUseCase;
import com.sport.formuladata.domain.port.inbound.GetMeetingsUseCase;
import com.sport.formuladata.domain.port.inbound.GetSessionsUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/f1/data")
@CrossOrigin(origins = "http://localhost:3000") // todo more generic
public class OpenF1Controller {
    private final FetchOpenF1DataUseCase fetchOpenF1DataUseCase;
    private final GetMeetingsUseCase getMeetingsUseCase;
    private final GetSessionsUseCase getSessionsUseCase;
    private final GetDriversUseCase getDriversUseCase;

    public OpenF1Controller(
            FetchOpenF1DataUseCase fetchOpenF1DataUseCase,
            GetMeetingsUseCase getMeetingsUseCase,
            GetSessionsUseCase getSessionsUseCase,
            GetDriversUseCase getDriversUseCase
    ) {
        this.fetchOpenF1DataUseCase = fetchOpenF1DataUseCase;
        this.getMeetingsUseCase = getMeetingsUseCase;
        this.getSessionsUseCase = getSessionsUseCase;
        this.getDriversUseCase = getDriversUseCase;
    }

    @PostMapping("/fetch")
    public String fetchAndStoreData() {
        fetchOpenF1DataUseCase.fetchAndStoreAllData();
        return "Data fetched, stored, and published to Kafka successfully";
    }

    @GetMapping("/meetings")
    public List<Meeting> getMeetings() {
        return getMeetingsUseCase.getAllMeetings();
    }

    @GetMapping("/sessions")
    public List<Session> getSessions() {
        return getSessionsUseCase.getAllSessions();
    }

    @GetMapping("/drivers")
    public List<Driver> getDrivers() {
        return getDriversUseCase.getAllDrivers();
    }

    @GetMapping("/season")
    public List<SessionDto> getSeason() {
        return getSessionsUseCase.getAllSessionDtos();
    }

    @GetMapping("/seasons")
    public List<SessionDto> getSessionDetailsByMeetingAndName(
            @RequestParam("year") Integer year,
            @RequestParam("sessionName") String sessionName) {
        return getSessionsUseCase.getSessionDtosByYearAndSessionName(year, sessionName);
    }
}