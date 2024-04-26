package com.bitirmeodev.halisahambe.domain.area.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AreaMapper {

    public static AreaDto toDto(Area area){
        return AreaDto.builder()
                .id(area.getId())
                .name(area.getName())
                .cityId(area.getCityId())
                .districtId(area.getDistrictId())
                .streetId(area.getStreetId())
                .photo(area.getPhoto())
                .build();
    }

    public static Area toEntity(Area area,AreaDto dto){
        area.setName(dto.getName());
        area.setCityId(dto.getCityId());
        area.setDistrictId(dto.getDistrictId());
        area.setStreetId(dto.getStreetId());
        area.setPhoto(dto.getPhoto());

        return area;
    }
}
