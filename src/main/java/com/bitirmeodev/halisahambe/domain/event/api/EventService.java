package com.bitirmeodev.halisahambe.domain.event.api;

import com.bitirmeodev.halisahambe.domain.event.web.EventSearchRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EventService {
    List<EventDto> getAll();
    EventDto getById(String id);

    List<EventDto> getUserEvents();

    List<EventDto> getByCityAndDistrictAndStreetAndArea(String cityId,String districtId,String streetId,String areaId);
    List<EventDto> getByCityAndDistrictAndStreet(String cityId,String districtId,String streetId);

    EventDto save(EventDto dto);
    EventDto update(String id,EventDto dto);

    void deleteUserOnEvent(String eventId, String userId);

    void exitOnEvent(String eventId);

    EventDto joinEvent(String eventId);

    void delete(String id);

    Boolean checkEventAuthority(String id);
}
