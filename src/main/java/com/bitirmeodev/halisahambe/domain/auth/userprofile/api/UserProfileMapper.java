package com.bitirmeodev.halisahambe.domain.auth.userprofile.api;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.web.UserProfileRequest;
import com.bitirmeodev.halisahambe.domain.auth.userprofile.web.UserProfileResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileMapper {

    public static UserProfileDto toDto(UserProfileRequest request){
        return UserProfileDto.builder()
                .user(UserDto.builder()
                        .id(request.getUserId())
                        .build())
                .photo(request.getPhoto())
                .build();
    }

    public static UserProfileResponse toResponse(UserProfileDto dto){
        return UserProfileResponse.builder()
                .id(dto.getId())
                .user(dto.getUser())
                .photo(dto.getPhoto())
                .build();
    }

    public static List<UserProfileResponse> toDataResponse(List<UserProfileDto> dtos){
        return dtos.stream().map(UserProfileMapper::toResponse).toList();
    }
}
