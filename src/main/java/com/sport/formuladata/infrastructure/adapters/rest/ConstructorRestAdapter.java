package com.sport.formuladata.infrastructure.adapters.rest;

import com.sport.formuladata.domain.dtos.ConstructorDto;
import com.sport.formuladata.domain.ports.ConstructorServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/constructors")
public class ConstructorRestAdapter {
    private final ConstructorServicePort service;

    public ConstructorRestAdapter(ConstructorServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConstructorDto> create(@RequestBody ConstructorDto constructorDto) {
        return ResponseEntity.ok(service.create(constructorDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructorDto> findById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ConstructorDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructorDto> update(@PathVariable String id, @RequestBody ConstructorDto constructorDto) {
        return ResponseEntity.ok(service.update(id, constructorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}