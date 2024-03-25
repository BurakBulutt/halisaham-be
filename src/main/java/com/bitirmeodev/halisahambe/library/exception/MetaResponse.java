package com.bitirmeodev.halisahambe.library.exception;

import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetaResponse {
    private int code;
    private String message;

    public MetaResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static MetaResponse of(int code, String message){
        return new MetaResponse(code,message);
    }
    public static MetaResponse success(){
        return new MetaResponse(MessageCodes.SUCCESS.getCode(),"Success");
    }

}
