package com.bitirmeodev.halisahambe.domain.auth.user.api;

import com.bitirmeodev.halisahambe.domain.auth.user.web.UserRequest;
import com.bitirmeodev.halisahambe.domain.auth.user.web.UserResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserDto toDto(UserRequest request){
        return UserDto.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(UserDto dto){
        return UserResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .userType(dto.getUserType())
                .verificationCode(dto.getVerificationCode())
                .changePasswordCode(dto.getChangePasswordCode())
                .isVerified(dto.getIsVerified())
                .build();
    }

    public static List<UserResponse> toDataResponse(List<UserDto> dtos){
        return dtos.stream().map(UserMapper::toResponse).toList();
    }
}
