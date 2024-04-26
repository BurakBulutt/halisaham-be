package com.bitirmeodev.halisahambe.domain.street.api;

import java.util.List;

public interface StreetService {
    List<StreetDto> getAll();
    List<StreetDto> getAllByDistrictId(String id);
    StreetDto getById(String id);
    StreetDto save(StreetDto dto);
    StreetDto update(String id, StreetDto dto);
    void delete(String id);
}
