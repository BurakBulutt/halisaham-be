package com.bitirmeodev.halisahambe.domain.event.web;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {
    private String id;
    private Long expirationDate;
    private String cityId;
    private String districtId;
    private String streetId;
    private Integer maxPeople;
    private String title;
    private String description;
    private UserDto admin;
    private List<UserDto> users;
    private AreaDto area;
}
