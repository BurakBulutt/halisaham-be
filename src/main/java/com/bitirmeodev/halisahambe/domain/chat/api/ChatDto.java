package com.bitirmeodev.halisahambe.domain.chat.api;

import com.bitirmeodev.halisahambe.domain.message.api.MessageDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private String id;
    private String eventId;
    private List<MessageDto> messages;
}
