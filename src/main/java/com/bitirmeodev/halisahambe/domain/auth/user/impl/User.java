package com.bitirmeodev.halisahambe.domain.auth.user.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserType;
import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = User.TABLE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    public static final String TABLE = "users";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isVerified;

    private String verificationCode;

    private String changePasswordCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;


}
