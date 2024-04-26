package com.bitirmeodev.halisahambe.domain.city.impl;

import com.bitirmeodev.halisahambe.domain.city.api.CityDto;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.domain.district.impl.DistrictMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CityMapper {

    public static CityDto toDto(City city, DistrictService districtService){
        return CityDto.builder()
                .id(city.getId())
                .code(city.getCode())
                .name(city.getName())
                .districts(districtService.getAllByCityId(city.getId()))
                .build();
    }

    public static City toEntity(City city,CityDto dto){
        city.setName(dto.getName());
        city.setCode(dto.getCode());
        return city;
    }


}
