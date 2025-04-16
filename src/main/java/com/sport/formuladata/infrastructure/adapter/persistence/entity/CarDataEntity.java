package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime date;

    private Integer rpm;

    private Integer speed;

    private Integer gear;

    private Float throttle;

    private Float brake;

    private Boolean drs;

    @Column(name = "x_position")
    private Float xPosition;

    @Column(name = "y_position")
    private Float yPosition;

    @Column(name = "z_position")
    private Float zPosition;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public Float getXPosition() {
        return xPosition;
    }

    public void setXPosition(Float xPosition) {
        this.xPosition = xPosition;
    }

    public Float getYPosition() {
        return yPosition;
    }

    public void setYPosition(Float yPosition) {
        this.yPosition = yPosition;
    }

    public Float getZPosition() {
        return zPosition;
    }

    public void setZPosition(Float zPosition) {
        this.zPosition = zPosition;
    }
}