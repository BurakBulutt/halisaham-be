package com.bitirmeodev.halisahambe.domain.message.web.websocket;

import com.bitirmeodev.halisahambe.domain.message.web.MessageRequest;
import com.bitirmeodev.halisahambe.domain.message.web.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageResponse sendMessage(@Payload MessageRequest request) {
        return null;
    }
}
