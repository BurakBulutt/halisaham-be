package com.bitirmeodev.halisahambe.domain.message.web;

import com.bitirmeodev.halisahambe.domain.message.api.MessageMapper;
import com.bitirmeodev.halisahambe.domain.message.api.MessageService;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("messages")
@RequiredArgsConstructor
public class MessageController extends BaseController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("find-by-chat-id/{chatId}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<DataResponse<MessageResponse>> getByChatId(@PathVariable String chatId){
        List<MessageResponse> dataResponse = MessageMapper.toDataResponse(messageService.getByChatId(chatId));
        return response(dataResponse);
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyRole('user','admin')")
    public Response<MessageResponse> save(@RequestBody MessageRequest request){
        MessageResponse response = MessageMapper.toResponse(messageService.createMessage(MessageMapper.toDto(request)));
        messagingTemplate.convertAndSend("/topic/messages", response);
        return response(response);
    }
}
