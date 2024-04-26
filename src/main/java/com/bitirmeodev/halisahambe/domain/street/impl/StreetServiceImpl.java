package com.bitirmeodev.halisahambe.domain.street.impl;

import com.bitirmeodev.halisahambe.domain.street.api.StreetDto;
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
public class StreetServiceImpl implements StreetService {
    private final StreetRepository repository;

    @Override
    public List<StreetDto> getAll() {
        return repository.findAll().stream().map(StreetMapper::toDto).toList();
    }

    @Override
    public List<StreetDto> getAllByDistrictId(String id) {
        return repository.findAllByDistrictId(id).stream().map(StreetMapper::toDto).toList();
    }

    @Override
    public StreetDto getById(String id) {
        return repository.findById(id).map(StreetMapper::toDto).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Street.class.getSimpleName(),id));
    }

    @Override
    @Transactional
    public StreetDto save(StreetDto dto) {
        return StreetMapper.toDto(repository.save(StreetMapper.toEntity(new Street(),dto)));
    }

    @Override
    @Transactional
    public StreetDto update(String id, StreetDto dto) {
        Street street = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Street.class.getSimpleName(),id));
        return StreetMapper.toDto(repository.save(StreetMapper.toEntity(street,dto)));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Street street = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND, Street.class.getSimpleName(),id));
        repository.delete(street);
    }


}
