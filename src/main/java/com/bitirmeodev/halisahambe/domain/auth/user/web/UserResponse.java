package com.bitirmeodev.halisahambe.domain.auth.user.web;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String surname;
    private Date birthDate;
    private UserType userType;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean isVerified;
    private String verificationCode;
    private String changePasswordCode;
}
