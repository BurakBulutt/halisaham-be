package com.bitirmeodev.halisahambe.domain.area.api;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDto {
    private String id;
    private String name;
    private String districtId;
    private String streetId;
    private String cityId;
    private byte[] photo;
    private MultipartFile multipartFile;
}
