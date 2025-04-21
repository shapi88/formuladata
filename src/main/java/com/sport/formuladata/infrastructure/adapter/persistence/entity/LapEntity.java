package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "laps")
public class LapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lap_id")
    private Integer lapId;

    @ManyToOne
    @JoinColumn(name = "session_key")
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "driver_number", referencedColumnName = "driver_number")
    private DriverEntity driver;

    @Column(name = "lap_number")
    private Integer lapNumber;

    @Column(name = "lap_duration")
    private Float lapDuration;
    
    @Column(name = "i1_speed")
    private Float iOneSpeed;
    
    @Column(name = "i2_speed")
    private Float iTwoSpeed;
    
    @Column(name = "st_speed")
    private Float stSpeed;

    @Column(name = "sector_1_duration")
    private Float sector1Duration;

    @Column(name = "sector_2_duration")
    private Float sector2Duration;

    @Column(name = "sector_3_duration")
    private Float sector3Duration;

    @Column(name = "is_pit_out_lap")
    private Boolean isPitOutLap;

    @Column(name = "date_start")
    private ZonedDateTime dateStart;

    public Integer getLapId() {
        return lapId;
    }

    public void setLapId(Integer lapId) {
        this.lapId = lapId;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public Integer getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(Integer lapNumber) {
        this.lapNumber = lapNumber;
    }

    public Float getLapDuration() {
        return lapDuration;
    }

    public void setLapDuration(Float lapDuration) {
        this.lapDuration = lapDuration;
    }

    public Float getIOneSpeed() {
        return iOneSpeed;
    }

    public void setIOneSpeed(Float iOneSpeed) {
        this.iOneSpeed = iOneSpeed;
    }

    public Float getITwoSpeed() {
        return iTwoSpeed;
    }

    public void setITwoSpeed(Float iTwoSpeed) {
        this.iTwoSpeed = iTwoSpeed;
    }

    public Float getStSpeed() {
        return stSpeed;
    }

    public void setStSpeed(Float stSpeed) {
        this.stSpeed = stSpeed;
    }

    public Float getSector1Duration() {
        return sector1Duration;
    }

    public void setSector1Duration(Float sector1Duration) {
        this.sector1Duration = sector1Duration;
    }

    public Float getSector2Duration() {
        return sector2Duration;
    }

    public void setSector2Duration(Float sector2Duration) {
        this.sector2Duration = sector2Duration;
    }

    public Float getSector3Duration() {
        return sector3Duration;
    }

    public void setSector3Duration(Float sector3Duration) {
        this.sector3Duration = sector3Duration;
    }

    public Boolean getIsPitOutLap() {
        return isPitOutLap;
    }

    public void setIsPitOutLap(Boolean isPitOutLap) {
        this.isPitOutLap = isPitOutLap;
    }

    public ZonedDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(ZonedDateTime dateStart) {
        this.dateStart = dateStart;
    }
}