package com.bitirmeodev.halisahambe.domain.area.api;

import java.util.List;

public interface AreaService {
    List<AreaDto> getAll();
    AreaDto getById(String id);
    AreaDto save(AreaDto dto);
    AreaDto update(String id,AreaDto dto);
    void delete(String id);
}
