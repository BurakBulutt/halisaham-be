package com.bitirmeodev.halisahambe.domain.area.web;


public record AreaRequest(
        String name,
        String cityId,
        String districtId,
        String streetId,
        String photoUrl
) {
}
