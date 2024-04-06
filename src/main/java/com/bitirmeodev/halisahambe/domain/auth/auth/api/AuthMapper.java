package com.bitirmeodev.halisahambe.domain.auth.auth.api;

import com.bitirmeodev.halisahambe.domain.auth.auth.web.LoginResponse;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.RegisterResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMapper {

    public static LoginResponse toLoginResponse(TokenDto dto){
        return new LoginResponse(dto.getToken());
    }

    public static RegisterResponse toRegisterResponse(TokenDto dto){
        return new RegisterResponse(dto.getToken());
    }
}
