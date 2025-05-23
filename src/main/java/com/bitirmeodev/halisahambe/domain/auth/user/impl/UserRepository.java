package com.bitirmeodev.halisahambe.domain.auth.user.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByVerificationCode(String code);

    Optional<User> findByUserType(UserType userType);

}
