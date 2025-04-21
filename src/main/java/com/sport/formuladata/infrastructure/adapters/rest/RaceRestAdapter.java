package com.sport.formuladata.infrastructure.adapters.rest;

import com.sport.formuladata.domain.dtos.RaceDto;
import com.sport.formuladata.domain.ports.RaceServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceRestAdapter {
    private final RaceServicePort service;

    public RaceRestAdapter(RaceServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RaceDto> create(@RequestBody RaceDto raceDto) {
        return ResponseEntity.ok(service.create(raceDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceDto> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RaceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceDto> update(@PathVariable Integer id, @RequestBody RaceDto raceDto) {
        return ResponseEntity.ok(service.update(id, raceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}