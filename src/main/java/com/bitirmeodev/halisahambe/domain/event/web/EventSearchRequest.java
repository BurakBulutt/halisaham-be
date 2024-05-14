package com.bitirmeodev.halisahambe.domain.event.web;

public record EventSearchRequest(
        String cityId,
        String districtId,
        String streetId,
        String areaId
        ) {
}
