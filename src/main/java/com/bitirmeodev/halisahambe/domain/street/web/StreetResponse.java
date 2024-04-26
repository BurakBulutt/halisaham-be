package com.bitirmeodev.halisahambe.domain.street.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreetResponse {
    private String id;
    private String name;
    private String districtId;
}
