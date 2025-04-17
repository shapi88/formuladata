package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

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

    @ManyToOne
    @JoinColumn(name = "meeting_key")
    private MeetingEntity meeting;

    @Column(name = "gap_to_leader")
    private Float gapToLeader;

    @Column(name = "interval_to_ahead")
    private Float intervalToAhead;

    @Column(name = "date")
    private ZonedDateTime date;

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

    public MeetingEntity getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingEntity meeting) {
        this.meeting = meeting;
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}