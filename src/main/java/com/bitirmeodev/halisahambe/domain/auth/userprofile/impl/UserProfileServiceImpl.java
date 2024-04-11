package com.bitirmeodev.halisahambe.domain.auth.userprofile.impl;

import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileDto;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repository;

    @Override
    public UserProfileDto getById(String id) {
        return repository.findById(id).map(UserProfileMapper::toDto).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));
    }

    @Override
    public UserProfileDto getByUserId(String id) {
        return repository.findByUserId(id).map(UserProfileMapper::toDto).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));

    }

    @Override
    public UserProfileDto save(UserProfileDto dto) {
        return UserProfileMapper.toDto(repository.save(UserProfileMapper.toEntity(new UserProfile(),dto)));
    }

    @Override
    public UserProfileDto update(String id, UserProfileDto dto) {
        UserProfile userProfile = repository.findByUserId(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));
        return UserProfileMapper.toDto(repository.save(UserProfileMapper.toEntity(userProfile,dto)));
    }

}
