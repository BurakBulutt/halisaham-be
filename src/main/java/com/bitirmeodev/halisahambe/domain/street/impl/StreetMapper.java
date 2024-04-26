package com.bitirmeodev.halisahambe.domain.street.impl;

import com.bitirmeodev.halisahambe.domain.street.api.StreetDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreetMapper {

    public static StreetDto toDto(Street street){
        return StreetDto.builder()
                .id(street.getId())
                .name(street.getName())
                .districtId(street.getDistrictId())
                .build();
    }

    public static Street toEntity(Street street, StreetDto dto){
        street.setName(dto.getName());
        street.setDistrictId(dto.getDistrictId());
        return street;
    }
}
