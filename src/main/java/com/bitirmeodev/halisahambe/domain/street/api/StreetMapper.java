package com.bitirmeodev.halisahambe.domain.street.api;

import com.bitirmeodev.halisahambe.domain.street.web.StreetRequest;
import com.bitirmeodev.halisahambe.domain.street.web.StreetResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreetMapper {

    public static StreetDto toDto(StreetRequest request){
        return StreetDto.builder()
                .name(request.name())
                .districtId(request.districtId())
                .build();
    }

    public static StreetResponse toResponse(StreetDto dto){
        return StreetResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .districtId(dto.getDistrictId())
                .build();
    }

    public static List<StreetResponse> toDataResponse(List<StreetDto> dtoList){
        return dtoList.stream().map(StreetMapper::toResponse).toList();
    }
}
