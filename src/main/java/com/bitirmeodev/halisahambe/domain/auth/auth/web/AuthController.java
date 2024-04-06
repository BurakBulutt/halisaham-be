package com.bitirmeodev.halisahambe.domain.auth.auth.web;

import com.bitirmeodev.halisahambe.domain.auth.auth.api.AuthMapper;
import com.bitirmeodev.halisahambe.domain.auth.auth.impl.AuthServiceImpl;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthServiceImpl authService;

    @PostMapping("login")
    public Response<LoginResponse> login(@RequestBody LoginRequest request){
        return response(AuthMapper.toLoginResponse(authService.login(request)));
    }

    @PostMapping("register")
    public Response<RegisterResponse> register(@RequestBody RegisterRequest request){
        return response(AuthMapper.toRegisterResponse(authService.register(request)));
    }
}
