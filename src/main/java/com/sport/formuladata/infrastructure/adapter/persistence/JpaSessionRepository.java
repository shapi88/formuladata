package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaSessionRepository extends JpaRepository<SessionEntity, Integer> {
    @Query("SELECT s FROM SessionEntity s " +
        "LEFT JOIN FETCH s.meeting m " +
        "LEFT JOIN FETCH s.laps l " +
        "LEFT JOIN FETCH l.driver d")
    List<SessionEntity> findAllWithRelations();

    @Query("SELECT s FROM SessionEntity s " +
           "LEFT JOIN FETCH s.meeting m " +
           "LEFT JOIN FETCH s.laps l " +
           "LEFT JOIN FETCH l.driver d " +
           "WHERE m.year = :year " +
           "AND s.sessionName = :sessionName" )
    List<SessionEntity> findByYearWithRelations(@Param("year") Integer year, @Param("sessionName") String sessionName);
}