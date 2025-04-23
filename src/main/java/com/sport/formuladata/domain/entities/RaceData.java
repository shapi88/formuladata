package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "race_data")
@IdClass(RaceDataId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceData {
    @Id
    @Column(name = "race_id", nullable = false)
    private Integer raceId;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "position_display_order", nullable = false)
    private Integer positionDisplayOrder;

    @Column(name = "position_number")
    private Integer positionNumber;

    @Column(name = "position_text", length = 4, nullable = false)
    private String positionText;

    @Column(name = "driver_number", length = 3, nullable = false)
    private String driverNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructor_id", nullable = false)
    private Constructor constructor;

    @Column(name = "practice_time", length = 20)
    private String practiceTime;

    @Column(name = "practice_time_millis")
    private Integer practiceTimeMillis;

    @Column(name = "practice_gap", length = 20)
    private String practiceGap;

    @Column(name = "practice_gap_millis")
    private Integer practiceGapMillis;

    @Column(name = "practice_interval", length = 20)
    private String practiceInterval;

    @Column(name = "practice_interval_millis")
    private Integer practiceIntervalMillis;

    @Column(name = "practice_laps")
    private Integer practiceLaps;

    @Column(name = "qualifying_time", length = 20)
    private String qualifyingTime;

    @Column(name = "qualifying_time_millis")
    private Integer qualifyingTimeMillis;

    @Column(name = "qualifying_q1", length = 20)
    private String qualifyingQ1;

    @Column(name = "qualifying_q1_millis")
    private Integer qualifyingQ1Millis;

    @Column(name = "qualifying_q2", length = 20)
    private String qualifyingQ2;

    @Column(name = "qualifying_q2_millis")
    private Integer qualifyingQ2Millis;

    @Column(name = "qualifying_q3", length = 20)
    private String qualifyingQ3;

    @Column(name = "qualifying_q3_millis")
    private Integer qualifyingQ3Millis;

    @Column(name = "qualifying_gap", length = 20)
    private String qualifyingGap;

    @Column(name = "qualifying_gap_millis")
    private Integer qualifyingGapMillis;

    @Column(name = "qualifying_interval", length = 20)
    private String qualifyingInterval;

    @Column(name = "qualifying_interval_millis")
    private Integer qualifyingIntervalMillis;

    @Column(name = "qualifying_laps")
    private Integer qualifyingLaps;

    @Column(name = "starting_grid_position_qualification_position_number")
    private Integer startingGridPositionQualificationPositionNumber;

    @Column(name = "starting_grid_position_qualification_position_text", length = 4)
    private String startingGridPositionQualificationPositionText;

    @Column(name = "starting_grid_position_grid_penalty", length = 20)
    private String startingGridPositionGridPenalty;

    @Column(name = "starting_grid_position_grid_penalty_positions")
    private Integer startingGridPositionGridPenaltyPositions;

    @Column(name = "starting_grid_position_time", length = 20)
    private String startingGridPositionTime;

    @Column(name = "starting_grid_position_time_millis")
    private Integer startingGridPositionTimeMillis;

    @Column(name = "race_shared_car")
    private Boolean raceSharedCar;

    @Column(name = "race_laps")
    private Integer raceLaps;

    @Column(name = "race_time", length = 20)
    private String raceTime;

    @Column(name = "race_time_millis")
    private Integer raceTimeMillis;

    @Column(name = "race_time_penalty", length = 20)
    private String raceTimePenalty;

    @Column(name = "race_time_penalty_millis")
    private Integer raceTimePenaltyMillis;

    @Column(name = "race_gap", length = 20)
    private String raceGap;

    @Column(name = "race_gap_millis")
    private Integer raceGapMillis;

    @Column(name = "race_gap_laps")
    private Integer raceGapLaps;

    @Column(name = "race_interval", length = 20)
    private String raceInterval;

    @Column(name = "race_interval_millis")
    private Integer raceIntervalMillis;

    @Column(name = "race_reason_retired", length = 100)
    private String raceReasonRetired;

    @Column(name = "race_points", precision = 8, scale = 2)
    private BigDecimal racePoints;

    @Column(name = "race_pole_position")
    private Boolean racePolePosition;

    @Column(name = "race_qualification_position_number")
    private Integer raceQualificationPositionNumber;

    @Column(name = "race_qualification_position_text", length = 4)
    private String raceQualificationPositionText;

    @Column(name = "race_grid_position_number")
    private Integer raceGridPositionNumber;

    @Column(name = "race_grid_position_text", length = 2)
    private String raceGridPositionText;

    @Column(name = "race_positions_gained")
    private Integer racePositionsGained;

    @Column(name = "race_pit_stops")
    private Integer racePitStops;

    @Column(name = "race_fastest_lap")
    private Boolean raceFastestLap;

    @Column(name = "race_driver_of_the_day")
    private Boolean raceDriverOfTheDay;

    @Column(name = "race_grand_slam")
    private Boolean raceGrandSlam;

    @Column(name = "fastest_lap_lap")
    private Integer fastestLapLap;

    @Column(name = "fastest_lap_time", length = 20)
    private String fastestLapTime;

    @Column(name = "fastest_lap_time_millis")
    private Integer fastestLapTimeMillis;

    @Column(name = "fastest_lap_gap", length = 20)
    private String fastestLapGap;

    @Column(name = "fastest_lap_gap_millis")
    private Integer fastestLapGapMillis;

    @Column(name = "fastest_lap_interval", length = 20)
    private String fastestLapInterval;

    @Column(name = "fastest_lap_interval_millis")
    private Integer fastestLapIntervalMillis;

    @Column(name = "pit_stop_stop")
    private Integer pitStopStop;

    @Column(name = "pit_stop_lap")
    private Integer pitStopLap;

    @Column(name = "pit_stop_time", length = 20)
    private String pitStopTime;

    @Column(name = "pit_stop_time_millis")
    private Integer pitStopTimeMillis;

    @Column(name = "driver_of_the_day_percentage", precision = 4, scale = 1)
    private BigDecimal driverOfTheDayPercentage;
}