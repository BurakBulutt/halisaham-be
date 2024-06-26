package com.bitirmeodev.halisahambe.domain.message.api;

import java.util.List;

public interface MessageService {
    List<MessageDto> getByChatId(String chatId);
    MessageDto createMessage(MessageDto messageDto);
}
