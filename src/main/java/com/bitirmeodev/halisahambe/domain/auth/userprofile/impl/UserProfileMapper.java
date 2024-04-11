package com.bitirmeodev.halisahambe.domain.auth.userprofile.impl;

import com.bitirmeodev.halisahambe.domain.auth.userprofile.api.UserProfileDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileMapper {

    public static UserProfileDto toDto(UserProfile userProfile){
        return UserProfileDto.builder()
                .id(userProfile.getId())
                .userId(userProfile.getUserId())
                .photo(userProfile.getPhoto())
                .build();
    }

    public static UserProfile toEntity(UserProfile userProfile,UserProfileDto dto){
        userProfile.setUserId(dto.getUserId());
        userProfile.setPhoto(dto.getPhoto());

        return userProfile;
    }
}
