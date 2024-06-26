package com.bitirmeodev.halisahambe.domain.message.web.websocket;

import com.bitirmeodev.halisahambe.domain.message.web.MessageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class MessageListResponse {
    private final List<MessageResponse> messageResponseList;
}
