package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.Session;
import com.sport.formuladata.domain.port.outbound.SessionRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class SessionRepositoryAdapter implements SessionRepositoryPort {
    private static final Logger LOGGER = Logger.getLogger(SessionRepositoryAdapter.class.getName());
    private final JpaSessionRepository jpaRepository;
    private final JpaMeetingRepository jpaMeetingRepository;

    public SessionRepositoryAdapter(JpaSessionRepository jpaRepository, JpaMeetingRepository jpaMeetingRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaMeetingRepository = jpaMeetingRepository;
    }

    @Override
    public void saveAll(List<Session> sessions) {
        LOGGER.info("Saving " + sessions.size() + " session entries");
        List<SessionEntity> entities = sessions.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<Session> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private SessionEntity toEntity(Session session) {
        SessionEntity entity = new SessionEntity();
        entity.setSessionKey(session.sessionKey());
        if (session.meetingKey() != null) {
            jpaMeetingRepository.findById(session.meetingKey())
                    .ifPresentOrElse(
                            entity::setMeeting,
                            () -> LOGGER.warning("No MeetingEntity found for meeting_key: " + session.meetingKey())
                    );
        }
        entity.setSessionType(session.sessionType());
        entity.setSessionName(session.sessionName());
        entity.setDateStart(session.dateStart());
        entity.setDateEnd(session.dateEnd());
        return entity;
    }

    private Session toDomain(SessionEntity entity) {
        return new Session(
                entity.getSessionKey(),
                entity.getMeeting().getMeetingKey(),
                entity.getSessionType(),
                entity.getSessionName(),
                entity.getDateStart(),
                entity.getDateEnd()
        );
    }
}