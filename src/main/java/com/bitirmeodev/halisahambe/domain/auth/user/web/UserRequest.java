package com.bitirmeodev.halisahambe.domain.auth.user.web;

import java.util.Date;

public record UserRequest(
        String name,
        String surname,
        String email,
        String password
) {
}
