package com.bitirmeodev.halisahambe.domain.auth.user.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .userType(user.getUserType())
                .changePasswordCode(user.getChangePasswordCode())
                .verificationCode(user.getVerificationCode())
                .isVerified(user.getIsVerified())
                .build();
    }

    public static User toEntity(User user,UserDto dto){
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(user.getEmail() == null ? dto.getEmail() : user.getEmail());
        user.setUserType(dto.getUserType());
        user.setIsVerified(dto.getIsVerified());
        user.setChangePasswordCode(dto.getChangePasswordCode());
        user.setVerificationCode(dto.getVerificationCode());

        return user;
    }
}
