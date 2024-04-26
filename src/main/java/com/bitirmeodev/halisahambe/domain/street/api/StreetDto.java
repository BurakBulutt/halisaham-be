package com.bitirmeodev.halisahambe.domain.street.api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StreetDto {
    private String id;
    private String name;
    private String districtId;
}
