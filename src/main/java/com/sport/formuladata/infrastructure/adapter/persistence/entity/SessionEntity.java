package com.sport.formuladata.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "sessions")
public class SessionEntity {
    @Id
    @Column(name = "session_key")
    private Integer sessionKey;

    @ManyToOne
    @JoinColumn(name = "meeting_key")
    private MeetingEntity meeting;

    @Column(name = "session_type")
    private String sessionType;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "date_start")
    private ZonedDateTime dateStart;

    @Column(name = "date_end")
    private ZonedDateTime dateEnd;

    public Integer getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(Integer sessionKey) {
        this.sessionKey = sessionKey;
    }

    public MeetingEntity getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingEntity meeting) {
        this.meeting = meeting;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public ZonedDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(ZonedDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public ZonedDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(ZonedDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}