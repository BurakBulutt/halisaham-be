package com.bitirmeodev.halisahambe.domain.district.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,String> {
    List<District> findAllByCityId(String id);
}
