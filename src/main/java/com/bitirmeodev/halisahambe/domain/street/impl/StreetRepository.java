package com.bitirmeodev.halisahambe.domain.street.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street,String> {
    List<Street> findAllByDistrictId(String id);
}
