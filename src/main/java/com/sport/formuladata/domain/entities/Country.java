package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @Column(length = 100)
    private String id;

    @Column(name = "alpha2_code", length = 2, unique = true, nullable = false)
    private String alpha2Code;

    @Column(name = "alpha3_code", length = 3, unique = true, nullable = false)
    private String alpha3Code;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(length = 100)
    private String demonym;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", nullable = false)
    private Continent continent;
}