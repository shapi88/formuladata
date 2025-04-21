package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "constructor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Constructor {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

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

    @Column(name = "total_1_and_2_finishes", nullable = false)
    private Integer total1And2Finishes;

    @Column(name = "total_race_laps", nullable = false)
    private Integer totalRaceLaps;

    @Column(name = "total_podiums", nullable = false)
    private Integer totalPodiums;

    @Column(name = "total_podium_races", nullable = false)
    private Integer totalPodiumRaces;

    @Column(name = "total_points", precision = 8, scale = 2, nullable = false)
    private BigDecimal totalPoints;

    @Column(name = "total_championship_points", precision = 8, scale = 2, nullable = false)
    private BigDecimal totalChampionshipPoints;

    @Column(name = "total_pole_positions", nullable = false)
    private Integer totalPolePositions;

    @Column(name = "total_fastest_laps", nullable = false)
    private Integer totalFastestLaps;
}
