package com.bitirmeodev.halisahambe.domain.event.impl.eventuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventUserRepository extends JpaRepository<EventUser, String> {
    List<EventUser> findAllByEventId(String eventId);
    List<EventUser> findAllByUserId(String userId);
    Optional<EventUser> findByUserIdAndEventId(String userId, String eventId);
}
