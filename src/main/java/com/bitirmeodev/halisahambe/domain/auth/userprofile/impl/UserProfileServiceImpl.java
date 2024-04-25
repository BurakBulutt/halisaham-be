package com.bitirmeodev.halisahambe.domain.auth.userprofile.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileCreationEvent;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileDto;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repository;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public UserProfileDto getById(String id) {
        UserDto userDto = userService.getById(id);
        return repository.findById(id).map(userProfile -> UserProfileMapper.toDto(userProfile,userDto)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));
    }

    @Override
    public UserProfileDto getByUserId(String id) {
        UserDto userDto = userService.getById(id);
        return repository.findByUserId(id).map(userProfile -> UserProfileMapper.toDto(userProfile,userDto)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));
    }

    @Override
    public UserProfileDto getProfileUser(String token){
        String email = jwtUtil.extractUsername(token);
        UserDto user = userService.getByEmail(email);
        return repository.findByUserId(user.getId()).map(userProfile -> UserProfileMapper.toDto(userProfile,user)).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName()));
    }

    @Override
    public UserProfileDto save(UserProfileDto dto) {
        UserDto userDto = userService.getById(dto.getUser().getId());
        return UserProfileMapper.toDto(repository.save(UserProfileMapper.toEntity(new UserProfile(),dto)),userDto);
    }

    @Override
    public UserProfileDto update(String id, UserProfileDto dto) {
        UserDto userDto = userService.getById(dto.getUser().getId());
        UserProfile userProfile = repository.findById(id).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND,UserProfile.class.getSimpleName(),id));
        return UserProfileMapper.toDto(repository.save(UserProfileMapper.toEntity(userProfile,dto)),userDto);
    }

    @EventListener
    @Order(1)
    public void profileCreation(UserProfileCreationEvent event){
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(event.userId());
        userProfile.setPhoto(null);

        repository.save(userProfile);
    }

}
