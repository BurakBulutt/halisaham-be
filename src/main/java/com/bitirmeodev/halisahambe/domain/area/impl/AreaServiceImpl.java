package com.bitirmeodev.halisahambe.domain.area.impl;

import com.bitirmeodev.halisahambe.domain.area.api.AreaDto;
import com.bitirmeodev.halisahambe.domain.area.api.AreaService;
import com.bitirmeodev.halisahambe.domain.city.api.CityService;
import com.bitirmeodev.halisahambe.domain.district.api.DistrictService;
import com.bitirmeodev.halisahambe.domain.street.api.StreetService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {
    private final AreaRepository repository;
    private final DistrictService districtService;
    private final StreetService streetService;

    @Override
    public List<AreaDto> getAll() {
        return repository.findAll().stream().map(AreaMapper::toDto).toList();
    }

    @Override
    public AreaDto getById(String id) {
        return repository.findById(id).map(AreaMapper::toDto).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Area.class.getSimpleName(),id));
    }

    @Override
    public List<AreaDto> getByDistrictIdAndStreetId(String districtId, String streetId) {
        return repository.findAllByDistrictIdAndStreetId(districtId,streetId).stream().map(AreaMapper::toDto).toList();
    }

    @Override
    @Transactional
    @SneakyThrows
    public AreaDto save(AreaDto dto) {
        FileInputStream file = new FileInputStream(new File(dto.getPhotoUrl()));
        dto.setPhoto(file.readAllBytes());
        districtService.getById(dto.getDistrictId());
        streetService.getById(dto.getStreetId());

        return AreaMapper.toDto(repository.save(AreaMapper.toEntity(new Area(),dto)));
    }

    @Override
    @Transactional
    @SneakyThrows
    public AreaDto update(String id, AreaDto dto) {
        Area area = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Area.class.getSimpleName(),id));

        FileInputStream file = new FileInputStream(new File(dto.getPhotoUrl()));
        dto.setPhoto(file.readAllBytes());
        districtService.getById(dto.getDistrictId());
        streetService.getById(dto.getStreetId());

        return AreaMapper.toDto(repository.save(AreaMapper.toEntity(area,dto)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Area area = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Area.class.getSimpleName(),id));
        repository.delete(area);
    }
}
