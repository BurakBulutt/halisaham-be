package com.bitirmeodev.halisahambe.domain.message.api;

import com.bitirmeodev.halisahambe.domain.auth.user.api.UserDto;
import com.bitirmeodev.halisahambe.domain.message.web.MessageRequest;
import com.bitirmeodev.halisahambe.domain.message.web.MessageResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageMapper {

    public static MessageDto toDto(MessageRequest request){
        return MessageDto.builder()
                .message(request.message())
                .chatId(request.chatId())
                .user(UserDto.builder().email(request.user()).build())
                .messageDate(new Date())
                .build();
    }

    public static MessageResponse toResponse(MessageDto messageDto){
        return MessageResponse.builder()
                .message(messageDto.getMessage())
                .user(messageDto.getUser())
                .chatId(messageDto.getChatId())
                .messageDate(messageDto.getMessageDate())
                .build();
    }

    public static List<MessageResponse> toDataResponse(List<MessageDto> messageDtos){
        return messageDtos.stream().map(MessageMapper::toResponse).toList();
    }
}
