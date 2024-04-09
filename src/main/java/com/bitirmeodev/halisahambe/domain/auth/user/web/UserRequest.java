package com.bitirmeodev.halisahambe.domain.auth.user.web;


public record UserRequest(
        String name,
        String surname,
        String email,
        String password
) {
}
