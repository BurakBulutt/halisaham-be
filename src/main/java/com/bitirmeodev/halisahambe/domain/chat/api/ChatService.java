package com.bitirmeodev.halisahambe.domain.chat.api;

public interface ChatService {
    ChatDto getByEventId(String eventId);
    ChatDto save(ChatDto chatDto);
    void delete(String id);
}
