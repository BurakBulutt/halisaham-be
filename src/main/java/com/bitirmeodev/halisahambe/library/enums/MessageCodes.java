package com.bitirmeodev.halisahambe.library.enums;

import lombok.Getter;

@Getter
public enum MessageCodes {

    SUCCESS(200,"general.success"),

    FAIL(500,"general.fail"),

    BAD_REQUEST(400,"general.badRequest"),

    NOT_FOUND(404,"general.notFound"),

    UNAUTHORIZED(401,"general.unauthorized"),

    ENTITY_NOT_FOUND(404,"general.entityNotFound"),

    ENTITY_ALREADY_EXISTS(1053,"general.entityAlreadyExists"),

    AUTHENTICATION_FAIL(1051,"general.fail"),

    NEED_VERIFICATION(1052,"general.needVerify");


    private String message;

    private int code;

    MessageCodes(int code, String message) {
        this.message = message;
        this.code = code;
    }


}
