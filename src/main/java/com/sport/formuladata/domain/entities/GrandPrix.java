package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grand_prix")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrandPrix {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "short_name", length = 100, nullable = false)
    private String shortName;

    @Column(length = 3, nullable = false)
    private String abbreviation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "total_races_held", nullable = false)
    private Integer totalRacesHeld;
}
