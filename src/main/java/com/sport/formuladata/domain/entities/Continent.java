package com.sport.formuladata.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "continent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Continent {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 2, unique = true, nullable = false)
    private String code;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String demonym;
}