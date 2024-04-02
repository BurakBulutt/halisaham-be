package com.bitirmeodev.halisahambe.domain.auth.auth.web;

public record LoginRequest(
        String email,
        String password
) {
}
