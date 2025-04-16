package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.Position;

import java.util.List;

public interface PositionRepositoryPort {
    void saveAll(List<Position> positions);
}