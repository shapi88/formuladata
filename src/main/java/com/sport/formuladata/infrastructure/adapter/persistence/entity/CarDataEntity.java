package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car_data")
public class CarDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_data_id")
    private Integer carDataId;

    @ManyToOne
    @JoinColumn(name = "session_key")
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "driver_number")
    private DriverEntity driver;

    @Column(name = "date")
    private ZonedDateTime date;

    private Integer rpm;

    private Integer speed;

    private Integer gear;

    private Float throttle;

    private Float brake;

    private Boolean drs;

    public Integer getCarDataId() {
        return carDataId;
    }

    public void setCarDataId(Integer carDataId) {
        this.carDataId = carDataId;
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Float getThrottle() {
        return throttle;
    }

    public void setThrottle(Float throttle) {
        this.throttle = throttle;
    }

    public Float getBrake() {
        return brake;
    }

    public void setBrake(Float brake) {
        this.brake = brake;
    }

    public Boolean getDrs() {
        return drs;
    }

    public void setDrs(Boolean drs) {
        this.drs = drs;
    }
}