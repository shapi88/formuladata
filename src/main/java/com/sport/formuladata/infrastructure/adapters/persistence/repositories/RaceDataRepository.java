package com.sport.formuladata.infrastructure.adapters.persistence.repositories;

import com.sport.formuladata.domain.entities.RaceData;
import com.sport.formuladata.domain.entities.RaceDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RaceDataRepository extends JpaRepository<RaceData, RaceDataId> {
    // Query derivation approach
    List<RaceData> findAllByRaceId(Integer raceId);

    // Custom JPQL query approach
    @Query("SELECT rd FROM RaceData rd WHERE rd.raceId = :raceId")
    List<RaceData> findAllByRaceIdCustom(@Param("raceId") Integer raceId);
}