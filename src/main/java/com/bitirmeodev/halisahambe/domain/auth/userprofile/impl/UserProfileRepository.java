package com.bitirmeodev.halisahambe.domain.auth.userprofile.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,String> {
    Optional<UserProfile> findByUserId(String id);
}
