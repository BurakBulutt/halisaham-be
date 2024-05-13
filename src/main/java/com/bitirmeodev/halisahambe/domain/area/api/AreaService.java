package com.bitirmeodev.halisahambe.domain.area.api;

import java.util.List;

public interface AreaService {
    List<AreaDto> getAll();
    AreaDto getById(String id);
    List<AreaDto> getByDistrictIdAndStreetId(String districtId, String streetId);
    AreaDto save(AreaDto dto);
    AreaDto update(String id,AreaDto dto);
    void delete(String id);
}
