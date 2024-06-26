package com.bitirmeodev.halisahambe.domain.message.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.message.api.MessageDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageMapper {

    public static MessageDto toDto(Message message, UserService userService){
        return MessageDto.builder()
                .id(message.getId())
                .chatId(message.getChatId())
                .user(userService.getById(message.getUserId()))
                .message(message.getMessage())
                .messageDate(message.getMessageDate())
                .build();
    }

    public static Message toEntity(Message message,MessageDto messageDto){
        message.setUserId(messageDto.getUser().getId());
        message.setChatId(messageDto.getChatId());
        message.setMessage(messageDto.getMessage());
        message.setMessageDate(messageDto.getMessageDate());
        return message;
    }
}
