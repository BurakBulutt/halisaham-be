package com.bitirmeodev.halisahambe.domain.event.api;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import com.bitirmeodev.halisahambe.domain.event.web.EventRequest;
import com.bitirmeodev.halisahambe.domain.event.web.EventResponse;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static EventDto toDto(EventRequest request){
        return EventDto.builder()
                .userId(JwtUtil.extractUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .cityId(request.getCityId())
                .districtId(request.getDistrictId())
                .streetId(request.getStreetId())
                .area(AreaDto.builder().id(request.getAreaId()).build())
                .maxPeople(request.getMaxPeople())
                .expirationDate(request.getExpirationDate())
                .build();
    }

    public static EventResponse toResponse(EventDto dto){
        return EventResponse.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .cityId(dto.getCityId())
                .districtId(dto.getDistrictId())
                .streetId(dto.getStreetId())
                .userId(dto.getUserId())
                .expirationDate(dto.getExpirationDate())
                .maxPeople(dto.getMaxPeople())
                .users(dto.getUsers())
                .area(dto.getArea())
                .build();
    }

    public static List<EventResponse> toDataResponse(List<EventDto> eventDtos){
        return eventDtos.stream().map(EventMapper::toResponse).toList();
    }
}
