package com.bitirmeodev.halisahambe.domain.area.web;

import org.springframework.web.multipart.MultipartFile;

public record AreaRequest(
        String name,
        String cityId,
        String districtId,
        String streetId
) {
}
