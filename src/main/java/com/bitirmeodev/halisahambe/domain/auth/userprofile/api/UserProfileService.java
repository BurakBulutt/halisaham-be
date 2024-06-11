package com.bitirmeodev.halisahambe.domain.auth.userprofile.api;

import java.util.List;

public interface UserProfileService {
    List<UserProfileDto> getAllByUserIdIn(List<String> userIds);
    UserProfileDto getById(String id);
    UserProfileDto getByUserId(String id);
    UserProfileDto getProfileUser(String token);
    UserProfileDto save(UserProfileDto dto);
    UserProfileDto update(String id,UserProfileDto dto);
}
