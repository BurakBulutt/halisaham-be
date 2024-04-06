package com.bitirmeodev.halisahambe.domain.auth.auth.web;

public record RegisterRequest(
        String email,
        String name,
        String surname,
        String password

) {

}
