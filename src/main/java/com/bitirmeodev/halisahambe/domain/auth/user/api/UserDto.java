package com.bitirmeodev.halisahambe.domain.auth.user.api;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String name;
    private String surname;
    private UserType userType;
    private String email;
    private String password;
    private Boolean isVerified;
    private String verificationCode;
    private String changePasswordCode;
}
