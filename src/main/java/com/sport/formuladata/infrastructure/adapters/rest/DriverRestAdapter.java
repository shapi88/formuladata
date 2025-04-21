package com.sport.formuladata.infrastructure.adapters.rest;

import com.sport.formuladata.domain.dtos.DriverDto;
import com.sport.formuladata.domain.ports.DriverServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverRestAdapter {
    private final DriverServicePort service;

    public DriverRestAdapter(DriverServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverDto> create(@RequestBody DriverDto driverDto) {
        return ResponseEntity.ok(service.create(driverDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> findById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DriverDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> update(@PathVariable String id, @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok(service.update(id, driverDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}