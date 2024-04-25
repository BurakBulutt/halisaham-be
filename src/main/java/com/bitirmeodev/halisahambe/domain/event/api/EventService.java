package com.bitirmeodev.halisahambe.domain.event.api;

import jakarta.transaction.Transactional;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();
    EventDto getById(String id);

    List<EventDto> getUserEvents();

    EventDto save(EventDto dto);
    EventDto update(String id,EventDto dto);

    @Transactional
    void deleteUserOnEvent(String eventId, String userId);

    @Transactional
    void exitOnEvent(String eventId);

    @Transactional
    void joinEvent(String eventId);

    void delete(String id);
}
