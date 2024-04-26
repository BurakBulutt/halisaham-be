package com.bitirmeodev.halisahambe.domain.district.impl;

import com.bitirmeodev.halisahambe.domain.city.api.CityService;
import com.bitirmeodev.halisahambe.domain.city.impl.CityMapper;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictDto;
import com.bitirmeodev.halisahambe.domain.street.api.StreetService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistrictMapper {

    public static DistrictDto toDto(District district, StreetService streetService){
        return DistrictDto.builder()
                .id(district.getId())
                .name(district.getName())
                .cityId(district.getCityId())
                .streets(streetService.getAllByDistrictId(district.getId()))
                .build();
    }

    public static District toEntity(District district, DistrictDto dto){
        district.setName(dto.getName());
        district.setCityId(dto.getCityId());
        return district;
    }
}
