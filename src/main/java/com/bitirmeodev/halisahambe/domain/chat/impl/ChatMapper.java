package com.bitirmeodev.halisahambe.domain.chat.impl;

import com.bitirmeodev.halisahambe.domain.chat.api.ChatDto;
import com.bitirmeodev.halisahambe.domain.message.api.MessageDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMapper {

    public static ChatDto toDto(Chat chat, List<MessageDto> messages){
        return ChatDto.builder()
                .id(chat.getId())
                .eventId(chat.getEventId())
                .messages(messages)
                .build();
    }

    public static Chat toEntity(Chat chat,ChatDto chatDto){
        chat.setEventId(chatDto.getEventId());
        chat.setMessages(chatDto.getMessages().stream().map(MessageDto::getId).toList());

        return chat;
    }
}
