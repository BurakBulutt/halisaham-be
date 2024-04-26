package com.bitirmeodev.halisahambe.domain.city.api;

import com.bitirmeodev.halisahambe.domain.district.api.DistrictDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CityDto {
    private String id;
    private String code;
    private String name;
    private List<DistrictDto> districts;
}
