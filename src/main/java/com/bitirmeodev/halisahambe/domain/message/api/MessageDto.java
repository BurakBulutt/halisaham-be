package com.bitirmeodev.halisahambe.domain.message.api;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String id;
    private UserDto user;
    private String chatId;
    private String message;
    private Date messageDate;
}
