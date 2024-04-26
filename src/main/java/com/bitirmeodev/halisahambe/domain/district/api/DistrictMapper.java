package com.bitirmeodev.halisahambe.domain.district.api;

import com.bitirmeodev.halisahambe.domain.city.api.CityDto;
import com.bitirmeodev.halisahambe.domain.district.web.DistrictRequest;
import com.bitirmeodev.halisahambe.domain.district.web.DistrictResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictMapper {

    public static DistrictDto toDto(DistrictRequest request){
        return DistrictDto.builder()
                .name(request.name())
                .cityId(request.cityId())
                .build();
    }

    public static DistrictResponse toResponse(DistrictDto dto){
        return DistrictResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cityId(dto.getCityId())
                .streets(dto.getStreets())
                .build();
    }

    public static List<DistrictResponse> toDataResponse(List<DistrictDto> dtoList){
        return dtoList.stream().map(DistrictMapper::toResponse).toList();
    }
}
