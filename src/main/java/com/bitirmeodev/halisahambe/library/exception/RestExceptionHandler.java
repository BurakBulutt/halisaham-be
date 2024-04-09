package com.bitirmeodev.halisahambe.library.exception;

import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler extends BaseController {
    private final MessageSource messageSource;

    @ExceptionHandler(BaseException.class)
    public Response<MetaResponse> handlerBaseException(BaseException baseException, Locale locale){
        MessageCodes messageCode = baseException.getMessageCode();
        String message = messageSource.getMessage(messageCode.getMessage(), baseException.getArgs(),locale);
        return new Response<>(MetaResponse.of(messageCode.getCode(),message));
    }
}
