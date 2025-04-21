package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "circuit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Circuit {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "previous_names", length = 255)
    private String previousNames;

    @Column(length = 6, nullable = false)
    private String type;

    @Column(length = 14, nullable = false)
    private String direction;

    @Column(name = "place_name", length = 100, nullable = false)
    private String placeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(precision = 10, scale = 6, nullable = false)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 6, nullable = false)
    private BigDecimal longitude;

    @Column(precision = 6, scale = 3, nullable = false)
    private BigDecimal length;

    @Column(nullable = false)
    private Integer turns;

    @Column(name = "total_races_held", nullable = false)
    private Integer totalRacesHeld;
}