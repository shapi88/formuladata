package com.sport.formuladata.infrastructure.adapters.rest;

import com.sport.formuladata.domain.dtos.RaceDataDto;
import com.sport.formuladata.domain.ports.RaceDataServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/racedata")
public class RaceDataRestAdapter {
    private final RaceDataServicePort service;

    public RaceDataRestAdapter(RaceDataServicePort service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RaceDataDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

 

    @GetMapping("/race/{raceId}")
    public ResponseEntity<List<RaceDataDto>> findAllByRaceId(@PathVariable Integer raceId) {
        return ResponseEntity.ok(service.findAllByRaceId(raceId));
    }

    @PostMapping
    public ResponseEntity<RaceDataDto> create(@RequestBody RaceDataDto raceDataDto) {
        return ResponseEntity.ok(service.create(raceDataDto));
    }

    @PutMapping("/{raceId}/{type}/{positionDisplayOrder}")
    public ResponseEntity<RaceDataDto> update(
            @PathVariable Integer raceId,
            @PathVariable String type,
            @PathVariable Integer positionDisplayOrder,
            @RequestBody RaceDataDto raceDataDto) {
        return ResponseEntity.ok(service.update(raceId, raceDataDto));
    }

    @DeleteMapping("/{raceId}/{type}/{positionDisplayOrder}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer raceId,
            @PathVariable String type,
            @PathVariable Integer positionDisplayOrder) {
        service.delete(raceId);
        return ResponseEntity.noContent().build();
    }
}