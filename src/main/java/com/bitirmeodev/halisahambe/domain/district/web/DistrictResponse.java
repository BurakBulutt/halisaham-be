package com.bitirmeodev.halisahambe.domain.district.web;

import com.bitirmeodev.halisahambe.domain.street.api.StreetDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictResponse {
    private String id;
    private String name;
    private String cityId;
    private List<StreetDto> streets;
}
