package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private String id;
    private UserDto user;
    private byte[] photo;
}
