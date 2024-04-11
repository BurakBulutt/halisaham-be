package com.bitirmeodev.halisahambe.domain.event.api;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();

    List<EventDto> getAllByUserId(String id);

    EventDto getById(String id);
    EventDto save(EventDto dto);
    EventDto update(String id,EventDto dto);
    void delete(String id);
}
