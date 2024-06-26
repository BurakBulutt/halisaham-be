package com.bitirmeodev.halisahambe.domain.message.impl;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserService;
import com.bitirmeodev.halisahambe.domain.message.api.MessageDto;
import com.bitirmeodev.halisahambe.domain.message.api.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final UserService userService;

    @Override
    public List<MessageDto> getByChatId(String chatId) {
        return repository.findAllByChatId(chatId).stream()
                .map(message -> MessageMapper.toDto(message,userService))
                .sorted(Comparator.comparing(MessageDto::getMessageDate))
                .toList();
    }

    @Override
    @Transactional
    public MessageDto createMessage(MessageDto messageDto) {
        messageDto.setUser(userService.getByEmail(messageDto.getUser().getEmail()));
        return MessageMapper.toDto(repository.save(MessageMapper.toEntity(new Message(),messageDto)),userService);
    }
}
