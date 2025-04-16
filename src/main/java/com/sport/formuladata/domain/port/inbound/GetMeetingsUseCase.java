package com.sport.formuladata.domain.port.inbound;

import com.sport.formuladata.domain.entity.Meeting;

import java.util.List;

public interface GetMeetingsUseCase {
    List<Meeting> getAllMeetings();
}