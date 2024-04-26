package com.bitirmeodev.halisahambe.domain.district.api;

import java.util.List;

public interface DistrictService {
    List<DistrictDto> getAll();
    List<DistrictDto> getAllByCityId(String id);
    DistrictDto getById(String id);
    DistrictDto save(DistrictDto dto);
    DistrictDto update(String id, DistrictDto dto);
    void delete(String id);
}
