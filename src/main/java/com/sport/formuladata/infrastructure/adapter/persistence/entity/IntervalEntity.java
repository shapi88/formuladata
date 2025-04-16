package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "intervals")
public class IntervalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interval_id")
    private Integer intervalId;

    @ManyToOne
    @JoinColumn(name = "session_key")
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "driver_number")
    private DriverEntity driver;

    @Column(name = "gap_to_leader")
    private Float gapToLeader;

    @Column(name = "interval_to_ahead")
    private Float intervalToAhead;

    private LocalDateTime date;

    public Integer getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(Integer intervalId) {
        this.intervalId = intervalId;
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

    public Float getGapToLeader() {
        return gapToLeader;
    }

    public void setGapToLeader(Float gapToLeader) {
        this.gapToLeader = gapToLeader;
    }

    public Float getIntervalToAhead() {
        return intervalToAhead;
    }

    public void setIntervalToAhead(Float intervalToAhead) {
        this.intervalToAhead = intervalToAhead;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}