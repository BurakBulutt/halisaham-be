package com.bitirmeodev.halisahambe.domain.event.api;

import com.bitirmeodev.halisahambe.domain.event.web.EventRequest;
import com.bitirmeodev.halisahambe.domain.event.web.EventResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static EventDto toDto(EventRequest request){
        return EventDto.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .city(request.getCity())
                .district(request.getDistrict())
                .street(request.getStreet())
                .maxPeople(request.getMaxPeople())
                .photo(request.getPhoto())
                .expirationDate(request.getExpirationDate())
                .build();
    }

    public static EventResponse toResponse(EventDto dto){
        return EventResponse.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .street(dto.getStreet())
                .userId(dto.getUserId())
                .expirationDate(dto.getExpirationDate())
                .photo(dto.getPhoto())
                .maxPeople(dto.getMaxPeople())
                .build();
    }

    public static List<EventResponse> toDataResponse(List<EventDto> eventDtos){
        return eventDtos.stream().map(EventMapper::toResponse).toList();
    }
}
