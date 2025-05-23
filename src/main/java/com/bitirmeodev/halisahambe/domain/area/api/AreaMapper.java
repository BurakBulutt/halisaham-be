package com.bitirmeodev.halisahambe.domain.area.api;

import com.bitirmeodev.halisahambe.domain.area.web.AreaRequest;
import com.bitirmeodev.halisahambe.domain.area.web.AreaResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AreaMapper {

    public static AreaDto toDto(AreaRequest request){
        return AreaDto.builder()
                .name(request.name())
                .districtId(request.districtId())
                .streetId(request.streetId())
                .photoUrl(request.photoUrl())
                .build();
    }

    public static AreaResponse toResponse(AreaDto dto){
        return AreaResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .districtId(dto.getDistrictId())
                .streetId(dto.getStreetId())
                .photo(dto.getPhoto())
                .build();
    }

    public static List<AreaResponse> toDataResponse(List<AreaDto> dtos){
        return dtos.stream().map(AreaMapper::toResponse).toList();
    }
}
