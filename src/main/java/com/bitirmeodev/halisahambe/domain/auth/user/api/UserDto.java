package com.bitirmeodev.halisahambe.domain.auth.user.api;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
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
