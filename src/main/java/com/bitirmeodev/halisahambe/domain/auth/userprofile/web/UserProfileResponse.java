package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private String id;
    private String userId;
    private byte[] photo;
}
