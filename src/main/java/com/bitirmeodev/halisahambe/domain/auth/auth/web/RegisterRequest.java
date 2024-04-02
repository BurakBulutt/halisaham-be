package com.bitirmeodev.halisahambe.domain.auth.auth.web;

import java.util.Date;

public record RegisterRequest(
        String email,
        String name,
        String surname,
        String password,
        Date birthdate,
        String phoneNumber

) {

}
