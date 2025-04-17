package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.Position;

import java.util.List;

public interface GetPositionsUseCase {
    List<Position> getAllPositions();
}