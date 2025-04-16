package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.infrastructure.adapter.persistence.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMeetingRepository extends JpaRepository<MeetingEntity, Integer> {
}