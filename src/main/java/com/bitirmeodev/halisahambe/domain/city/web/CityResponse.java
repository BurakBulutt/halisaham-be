package com.bitirmeodev.halisahambe.domain.city.web;

import com.bitirmeodev.halisahambe.domain.district.api.DistrictDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {
    private String id;
    private String code;
    private String name;
    private List<DistrictDto> districts;
}
