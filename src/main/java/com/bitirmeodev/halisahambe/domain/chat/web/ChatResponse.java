package com.bitirmeodev.halisahambe.domain.chat.web;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private String eventId;
}
