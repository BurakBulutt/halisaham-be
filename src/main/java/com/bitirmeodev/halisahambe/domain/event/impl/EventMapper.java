package com.bitirmeodev.halisahambe.domain.event.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaService;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.event.api.EventDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static EventDto toDto(Event event, List<UserDto> users, AreaService areaService, UserService userService) {

        return EventDto.builder()
                .id(event.getId())
                .cityId(event.getCityId())
                .districtId(event.getDistrictId())
                .streetId(event.getStreetId())
                .maxPeople(event.getMaxPeople())
                .title(event.getTitle())
                .description(event.getDescription())
                .adminUser(userService.getById(event.getUserId()))
                .area(areaService.getById(event.getAreaId()))
                .expirationDate(event.getExpirationDate())
                .users(users)
                .build();
    }

    public static Event toEntity(Event event,EventDto dto){
        event.setCityId(dto.getCityId());
        event.setDistrictId(dto.getDistrictId());
        event.setDescription(dto.getDescription());
        event.setStreetId(dto.getStreetId());
        event.setMaxPeople(dto.getMaxPeople());
        event.setTitle(dto.getTitle());
        event.setUserId(dto.getAdminUser().getId());
        event.setExpirationDate(dto.getExpirationDate());
        event.setAreaId(dto.getArea().getId());

        return event;
    }
}
