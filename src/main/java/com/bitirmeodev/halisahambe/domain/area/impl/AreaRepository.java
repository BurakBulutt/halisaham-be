package com.bitirmeodev.halisahambe.domain.area.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area,String> {
    List<Area> findAllByDistrictIdAndStreetId(String districtId, String streetId);
    List<Area> findAllByName(String name);
}
