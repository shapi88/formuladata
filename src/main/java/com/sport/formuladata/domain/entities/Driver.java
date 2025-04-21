package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(length = 3, nullable = false)
    private String abbreviation;

    @Column(name = "permanent_number", length = 2)
    private String permanentNumber;

    @Column(length = 6, nullable = false)
    private String gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @Column(name = "place_of_birth", length = 100, nullable = false)
    private String placeOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_of_birth_country_id", nullable = false)
    private Country countryOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_country_id", nullable = false)
    private Country nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_nationality_country_id")
    private Country secondNationality;

    @Column(name = "best_championship_position")
    private Integer bestChampionshipPosition;

    @Column(name = "best_starting_grid_position")
    private Integer bestStartingGridPosition;

    @Column(name = "best_race_result")
    private Integer bestRaceResult;

    @Column(name = "total_championship_wins", nullable = false)
    private Integer totalChampionshipWins;

    @Column(name = "total_race_entries", nullable = false)
    private Integer totalRaceEntries;

    @Column(name = "total_race_starts", nullable = false)
    private Integer totalRaceStarts;

    @Column(name = "total_race_wins", nullable = false)
    private Integer totalRaceWins;

    @Column(name = "total_race_laps", nullable = false)
    private Integer totalRaceLaps;

    @Column(name = "total_podiums", nullable = false)
    private Integer totalPodiums;

    @Column(name = "total_points", precision = 8, scale = 2, nullable = false)
    private BigDecimal totalPoints;

    @Column(name = "total_championship_points", precision = 8, scale = 2, nullable = false)
    private BigDecimal totalChampionshipPoints;

    @Column(name = "total_pole_positions", nullable = false)
    private Integer totalPolePositions;

    @Column(name = "total_fastest_laps", nullable = false)
    private Integer totalFastestLaps;

    @Column(name = "total_driver_of_the_day", nullable = false)
    private Integer totalDriverOfTheDay;

    @Column(name = "total_grand_slams", nullable = false)
    private Integer totalGrandSlams;
}
