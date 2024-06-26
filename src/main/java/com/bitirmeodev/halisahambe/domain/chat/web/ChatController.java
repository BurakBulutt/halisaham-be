package com.bitirmeodev.halisahambe.domain.chat.web;

import com.bitirmeodev.halisahambe.domain.chat.api.ChatMapper;
import com.bitirmeodev.halisahambe.domain.chat.api.ChatService;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chats")
@RequiredArgsConstructor
public class ChatController extends BaseController {
    private final ChatService chatService;

    @GetMapping("find-by-event/{eventId}")
    public Response<ChatResponse> getByEventId(@PathVariable String eventId){
        return response(ChatMapper.toDto(chatService.getByEventId(eventId)));
    }
}
