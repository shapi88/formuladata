package com.sport.formuladata.domain.port.outbound;

import com.sport.formuladata.domain.entity.Interval;

import java.util.List;

public interface IntervalRepositoryPort {
    void saveAll(List<Interval> intervals);
}