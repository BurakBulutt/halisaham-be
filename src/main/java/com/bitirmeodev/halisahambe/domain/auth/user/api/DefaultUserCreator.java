package com.bitirmeodev.halisahambe.domain.auth.user.api;

import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserServiceImpl;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserCreator {
    private static final String ADMIN_EMAIL = "admin@login.com";
    private static final String ADMIN_PASSWORD = "123456";

    private static final String USER_EMAIL = "user@login.com";
    private static final String USER_PASSWORD = "123456";


    private final PasswordEncoder encoder;
    private final UserServiceImpl userService;

    @EventListener(value = ApplicationReadyEvent.class)
    public void createAdminUser() {
        if (userService.getByRole(UserType.ADMIN) == null) {
            userService.save(UserDto.builder()
                    .name("Burak")
                    .surname("Bulut")
                    .userType(UserType.ADMIN)
                    .email(ADMIN_EMAIL)
                    .password(encoder.encode(ADMIN_PASSWORD))
                    .isVerified(Boolean.TRUE)
                    .verificationCode(null)
                    .changePasswordCode(null)
                    .build());
        }
        try{
            userService.getByEmail(USER_EMAIL);
        }catch (BaseException e){
            userService.save(UserDto.builder()
                    .name("Burak User")
                    .surname("Bulut")
                    .userType(UserType.USER)
                    .email(USER_EMAIL)
                    .password(encoder.encode(USER_PASSWORD))
                    .isVerified(Boolean.TRUE)
                    .verificationCode(null)
                    .changePasswordCode(null)
                    .build());
        }

    }
}
