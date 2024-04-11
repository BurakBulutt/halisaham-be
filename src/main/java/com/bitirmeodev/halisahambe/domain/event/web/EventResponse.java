package com.bitirmeodev.halisahambe.domain.event.web;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {
    private String id;
    private Date expirationDate;
    private String city;
    private String district;
    private String street;
    private Integer maxPeople;
    private String title;
    private String description;
    private byte[] photo;
    private String userId;
}
