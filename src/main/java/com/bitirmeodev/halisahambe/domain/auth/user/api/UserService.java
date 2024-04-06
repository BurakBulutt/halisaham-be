package com.bitirmeodev.halisahambe.domain.auth.user.api;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getById(String id);
    UserDto getByEmail(String email);
    UserDto save(UserDto dto);
    UserDto update(String id,UserDto dto);
    void delete(String id);

    @Transactional
    void verificateUser(String code);

    void sendVerificationMail();
}
