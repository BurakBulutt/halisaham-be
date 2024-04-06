package com.bitirmeodev.halisahambe.domain.auth.auth.impl;

import com.bitirmeodev.halisahambe.domain.auth.auth.web.RegisterRequest;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMapper {

    public static UserDto toUser(RegisterRequest request){
        return UserDto.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
