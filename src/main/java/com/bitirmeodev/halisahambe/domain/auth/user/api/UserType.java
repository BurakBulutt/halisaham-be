package com.bitirmeodev.halisahambe.domain.auth.user.api;

import lombok.Getter;

@Getter
public enum UserType {

    ADMIN("admin"),

    USER("user");


    private String role;

    UserType(String role) {
        this.role = role;
    }


}
