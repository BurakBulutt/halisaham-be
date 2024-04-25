package com.bitirmeodev.halisahambe.domain.event.api;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
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
    private List<UserDto> users;
}
