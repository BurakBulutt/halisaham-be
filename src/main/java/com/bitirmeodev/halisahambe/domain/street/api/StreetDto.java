package com.bitirmeodev.halisahambe.domain.street.api;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StreetDto implements Serializable {
    private String id;
    private String name;
    private String districtId;
}
