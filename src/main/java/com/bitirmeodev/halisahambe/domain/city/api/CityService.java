package com.bitirmeodev.halisahambe.domain.city.api;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();
    CityDto getById(String id);
    CityDto save(CityDto dto);
    CityDto update(String id,CityDto dto);
    void delete(String id);
}
