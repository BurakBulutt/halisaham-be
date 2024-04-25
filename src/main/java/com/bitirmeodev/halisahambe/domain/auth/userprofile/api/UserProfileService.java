package com.bitirmeodev.halisahambe.domain.auth.userprofile.api;

public interface UserProfileService {
    UserProfileDto getById(String id);
    UserProfileDto getByUserId(String id);

    UserProfileDto getProfileUser(String token);

    UserProfileDto save(UserProfileDto dto);
    UserProfileDto update(String id,UserProfileDto dto);
}
