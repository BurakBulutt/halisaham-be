package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileRequest {
    private String userId;
    private byte[] photo;
}
