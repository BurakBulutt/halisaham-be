package com.bitirmeodev.halisahambe.domain.auth.auth.impl;

import com.bitirmeodev.halisahambe.domain.auth.auth.api.TokenDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserCreationEvent;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.LoginRequest;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.RegisterRequest;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.User;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserServiceImpl;
import com.bitirmeodev.halisahambe.library.security.CustomUserDetails;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import com.bitirmeodev.halisahambe.library.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final ApplicationEventPublisher publisher;


    @Transactional
    public TokenDto register(RegisterRequest request) {
        UserDto user = AuthMapper.toUser(request);
        user.setIsVerified(Boolean.FALSE);
        user.setUserType(UserType.USER);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setVerificationCode(generateVerificationCode().toString());

        User saved = userService.saveUser(user);
        publisher.publishEvent(new UserCreationEvent(request.email(),user.getVerificationCode()));

        CustomUserDetails userDetails = new CustomUserDetails(saved);

        String token = jwtUtil.generateToken(userDetails);

        return new TokenDto(token);
    }

    public TokenDto login(LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
                )
        );

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(request.email());

        String token = jwtUtil.generateToken(userDetails);
        return new TokenDto(token);
    }


    public UUID generateVerificationCode(){
        return UUID.randomUUID();
    }





}
