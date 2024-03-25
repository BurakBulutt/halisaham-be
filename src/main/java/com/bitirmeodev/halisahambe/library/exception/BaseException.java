package com.bitirmeodev.halisahambe.library.exception;

import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private MessageCodes messageCode;
    private Object[] args;
    public BaseException(MessageCodes messageCode,Object... args) {
        this.messageCode = messageCode;
        this.args = args;
    }


}
