package com.bitirmeodev.halisahambe.domain.city.api;

import com.bitirmeodev.halisahambe.domain.city.web.CityRequest;
import com.bitirmeodev.halisahambe.domain.city.web.CityResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CityMapper {

    public static CityDto toDto(CityRequest request){
        return CityDto.builder()
                .name(request.name())
                .code(request.code())
                .build();
    }

    public static CityResponse toResponse(CityDto dto){
        return CityResponse.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .name(dto.getName())
                .districts(dto.getDistricts())
                .build();
    }

    public static List<CityResponse> toDataResponse(List<CityDto> dtoList){
        return dtoList.stream().map(CityMapper::toResponse).toList();
    }
}
