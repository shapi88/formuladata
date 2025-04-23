package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "race", uniqueConstraints = @UniqueConstraint(columnNames = {"year", "round"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer round;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grand_prix_id", nullable = false)
    private GrandPrix grandPrix;

    @Column(name = "official_name", length = 100, nullable = false)
    private String officialName;

    @Column(name = "qualifying_format", length = 20, nullable = false)
    private String qualifyingFormat;

    @Column(name = "sprint_qualifying_format", length = 20)
    private String sprintQualifyingFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_id", nullable = false)
    private Circuit circuit;

    @Column(name = "circuit_type", length = 6, nullable = false)
    private String circuitType;

    @Column(length = 14, nullable = false)
    private String direction;

    @Column(name = "course_length", precision = 6, scale = 3, nullable = false)
    private BigDecimal courseLength;

    @Column(nullable = false)
    private Integer turns;

    @Column(nullable = false)
    private Integer laps;

    @Column(precision = 6, scale = 3, nullable = false)
    private BigDecimal distance;

    @Column(name = "scheduled_laps")
    private Integer scheduledLaps;

    @Column(name = "scheduled_distance", precision = 6, scale = 3)
    private BigDecimal scheduledDistance;

    @Column(name = "drivers_championship_decider")
    private Boolean driversChampionshipDecider;

    @Column(name = "constructors_championship_decider")
    private Boolean constructorsChampionshipDecider;

    @Column(name = "pre_qualifying_date")
    private LocalDate preQualifyingDate;

    @Column(name = "pre_qualifying_time", length = 5)
    private String preQualifyingTime;

    @Column(name = "free_practice_1_date")
    private LocalDate freePractice1Date;

    @Column(name = "free_practice_1_time", length = 5)
    private String freePractice1Time;

    @Column(name = "free_practice_2_date")
    private LocalDate freePractice2Date;

    @Column(name = "free_practice_2_time", length = 5)
    private String freePractice2Time;

    @Column(name = "free_practice_3_date")
    private LocalDate freePractice3Date;

    @Column(name = "free_practice_3_time", length = 5)
    private String freePractice3Time;

    @Column(name = "free_practice_4_date")
    private LocalDate freePractice4Date;

    @Column(name = "free_practice_4_time", length = 5)
    private String freePractice4Time;

    @Column(name = "qualifying_1_date")
    private LocalDate qualifying1Date;

    @Column(name = "qualifying_1_time", length = 5)
    private String qualifying1Time;

    @Column(name = "qualifying_2_date")
    private LocalDate qualifying2Date;

    @Column(name = "qualifying_2_time", length = 5)
    private String qualifying2Time;

    @Column(name = "qualifying_date")
    private LocalDate qualifyingDate;

    @Column(name = "qualifying_time", length = 5)
    private String qualifyingTime;

    @Column(name = "sprint_qualifying_date")
    private LocalDate sprintQualifyingDate;

    @Column(name = "sprint_qualifying_time", length = 5)
    private String sprintQualifyingTime;

    @Column(name = "sprint_race_date")
    private LocalDate sprintRaceDate;

    @Column(name = "sprint_race_time", length = 5)
    private String sprintRaceTime;

    @Column(name = "warming_up_date")
    private LocalDate warmingUpDate;

    @Column(name = "warming_up_time", length = 5)
    private String warmingUpTime;
}