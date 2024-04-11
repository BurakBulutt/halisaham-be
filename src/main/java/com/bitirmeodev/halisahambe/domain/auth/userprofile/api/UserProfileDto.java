package com.bitirmeodev.halisahambe.domain.auth.userprofile.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    private String id;
    private String userId;
    private byte[] photo;
}
