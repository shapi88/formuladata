package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class DriverEntity {
    @Id
    @Column(name = "driver_number")
    private Integer driverNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_colour")
    private String teamColour;

    @Column(name = "country_code")
    private String countryCode;

    public Integer getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(Integer driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String teamColour) {
        this.teamColour = teamColour;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}