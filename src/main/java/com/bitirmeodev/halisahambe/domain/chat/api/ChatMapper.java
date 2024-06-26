package com.bitirmeodev.halisahambe.domain.chat.api;

import com.bitirmeodev.halisahambe.domain.chat.web.ChatResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMapper {

    public static ChatResponse toDto(ChatDto chat){
        return ChatResponse.builder()
                .id(chat.getId())
                .eventId(chat.getEventId())
                .build();
    }

}
