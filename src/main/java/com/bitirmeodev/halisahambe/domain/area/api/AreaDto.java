package com.bitirmeodev.halisahambe.domain.area.api;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDto implements Serializable {
    private String id;
    private String name;
    private String districtId;
    private String streetId;
    private String photoUrl;
    private byte[] photo;
}
