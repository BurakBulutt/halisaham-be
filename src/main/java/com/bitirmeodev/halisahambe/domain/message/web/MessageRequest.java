package com.bitirmeodev.halisahambe.domain.message.web;

public record MessageRequest(
        String message,
        String user,
        String chatId
) {
}
