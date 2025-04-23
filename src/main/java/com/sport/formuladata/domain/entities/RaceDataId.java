package com.sport.formuladata.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class RaceDataId implements Serializable {
    private Integer raceId;

    public RaceDataId() {}

    public RaceDataId(Integer raceId) {
        this.raceId = raceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceDataId that = (RaceDataId) o;
        return Objects.equals(raceId, that.raceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceId);
    }
}