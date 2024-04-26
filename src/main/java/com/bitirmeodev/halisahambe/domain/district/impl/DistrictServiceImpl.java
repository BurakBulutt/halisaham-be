package com.bitirmeodev.halisahambe.domain.district.impl;

import com.bitirmeodev.halisahambe.domain.district.api.DistrictDto;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.domain.street.api.StreetService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository repository;
    private final StreetService streetService;

    @Override
    public List<DistrictDto> getAll() {
        return repository.findAll().stream().map(district -> DistrictMapper.toDto(district,streetService)).toList();
    }

    @Override
    public List<DistrictDto> getAllByCityId(String id) {
        return repository.findAllByCityId(id).stream().map(district -> DistrictMapper.toDto(district,streetService)).toList();
    }

    @Override
    public DistrictDto getById(String id) {
        return repository.findById(id).map(district -> DistrictMapper.toDto(district,streetService)).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
    }

    @Override
    @Transactional
    public DistrictDto save(DistrictDto dto) {
        return DistrictMapper.toDto(repository.save(DistrictMapper.toEntity(new District(),dto)),streetService);
    }

    @Override
    @Transactional
    public DistrictDto update(String id, DistrictDto dto) {
        District district = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
        return DistrictMapper.toDto(repository.save(DistrictMapper.toEntity(district,dto)),streetService);
    }

    @Override
    @Transactional
    public void delete(String id) {
        District district = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
        repository.delete(district);
    }


}
