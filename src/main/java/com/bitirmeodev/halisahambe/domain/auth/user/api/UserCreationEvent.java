package com.bitirmeodev.halisahambe.domain.auth.user.api;

public record UserCreationEvent(
        String email,
        String verificationCode
) {
}
