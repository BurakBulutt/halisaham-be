package com.bitirmeodev.halisahambe.domain.city.impl;

import com.bitirmeodev.halisahambe.domain.city.api.CityDto;
import com.bitirmeodev.halisahambe.domain.city.api.CityService;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "cities")
public class CityServiceImpl implements CityService {
    private final CityRepository repository;
    private final DistrictService districtService;

    @Override
    @Cacheable
    public List<CityDto> getAll() {
        return repository.findAll().stream().map(city -> CityMapper.toDto(city, districtService)).toList();
    }

    @Override
    @Cacheable(key = "#id")
    public CityDto getById(String id) {
        return repository.findById(id).map(city -> CityMapper.toDto(city, districtService)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), id));
    }

    @Override
    @Transactional
    public CityDto save(CityDto dto) {
        return CityMapper.toDto(repository.save(CityMapper.toEntity(new City(), dto)), districtService);
    }

    @Override
    @Transactional
    public CityDto update(String id, CityDto dto) {
        City city = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), id));
        return CityMapper.toDto(repository.save(CityMapper.toEntity(city, dto)), districtService);
    }

    @Override
    @Transactional
    public void delete(String id) {
        City city = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), id));
        repository.delete(city);
    }


}
