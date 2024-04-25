package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.event.api.EventDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static EventDto toDto(Event event,List<UserDto> users) {

        return EventDto.builder()
                .id(event.getId())
                .city(event.getCity())
                .district(event.getDistrict())
                .street(event.getStreet())
                .maxPeople(event.getMaxPeople())
                .title(event.getTitle())
                .description(event.getDescription())
                .photo(event.getPhoto())
                .userId(event.getUserId())
                .expirationDate(event.getExpirationDate())
                .users(users)
                .build();
    }

    public static Event toEntity(Event event,EventDto dto){
        event.setCity(dto.getCity());
        event.setDistrict(dto.getDistrict());
        event.setDescription(dto.getDescription());
        event.setStreet(dto.getStreet());
        event.setMaxPeople(dto.getMaxPeople());
        event.setTitle(dto.getTitle());
        event.setPhoto(dto.getPhoto());
        event.setUserId(dto.getUserId());
        event.setExpirationDate(dto.getExpirationDate());

        return event;
    }
}
