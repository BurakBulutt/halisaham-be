package com.bitirmeodev.halisahambe.domain.auth.auth.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginResponse {
    private String token;
}
