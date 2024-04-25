package com.bitirmeodev.halisahambe.domain.auth.userprofile.api;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    private String id;
    private UserDto user;
    private byte[] photo;
}
