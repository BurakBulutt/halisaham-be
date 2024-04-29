package com.bitirmeodev.halisahambe.domain.district.api;

import com.bitirmeodev.halisahambe.domain.street.api.StreetDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DistrictDto implements Serializable {
    private String id;
    private String name;
    private String cityId;
    private List<StreetDto> streets;
}
