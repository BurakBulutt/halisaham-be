package com.bitirmeodev.halisahambe.domain.event.web;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {
    private Long expirationDate;
    private String cityId;
    private String districtId;
    private String streetId;
    private String areaId;
    private Integer maxPeople;
    private String title;
    private String description;
}
