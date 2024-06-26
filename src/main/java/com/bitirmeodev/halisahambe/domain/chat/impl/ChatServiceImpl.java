package com.bitirmeodev.halisahambe.domain.chat.impl;

import com.bitirmeodev.halisahambe.domain.chat.api.ChatDto;
import com.bitirmeodev.halisahambe.domain.chat.api.ChatService;
import com.bitirmeodev.halisahambe.domain.message.api.MessageService;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {
    private final ChatRepository repository;
    private final MessageService messageService;

    @Override
    public ChatDto getByEventId(String eventId) {
        return repository.findByEventId(eventId).map(chat -> ChatMapper.toDto(chat,messageService.getByChatId(chat.getId()))).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Chat.class.getSimpleName(),eventId));
    }

    @Override
    public ChatDto save(ChatDto chatDto) {
        return ChatMapper.toDto(repository.save(ChatMapper.toEntity(new Chat(),chatDto)),new ArrayList<>());
    }

    @Override
    public void delete(String id) {
        Chat chat = repository.findById(id).orElseThrow(()-> new BaseException(MessageCodes.ENTITY_NOT_FOUND,Chat.class.getSimpleName(),id));
        repository.delete(chat);
    }
}
