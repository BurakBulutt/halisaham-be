package com.bitirmeodev.halisahambe.domain.auth.auth.impl;

import com.bitirmeodev.halisahambe.domain.auth.auth.web.LoginRequest;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.LoginResponse;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.RegisterRequest;
import com.bitirmeodev.halisahambe.domain.auth.auth.web.RegisterResponse;
import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.User;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserRepository;
import com.bitirmeodev.halisahambe.library.security.JwtUtil;
import com.bitirmeodev.halisahambe.library.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;


    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setBirthdate(request.birthdate());
        user.setIsVerified(Boolean.TRUE);
        user.setPhoneNumber(request.phoneNumber());
        user.setUserType(UserType.ADMIN);

        repository.save(user);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

        String token = jwtUtil.generateToken(userDetails);

        RegisterResponse response = new RegisterResponse();
        response.setToken(token);

        return response;
    }

    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        User user = repository.findByEmail(request.email()).orElse(null);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }

}
