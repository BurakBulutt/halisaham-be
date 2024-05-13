package com.bitirmeodev.halisahambe.domain.area.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaResponse {
    private String id;
    private String name;
    private String districtId;
    private String streetId;
    private byte[] photo;
}
