package com.bitirmeodev.halisahambe.library.rest;

import com.bitirmeodev.halisahambe.library.exception.MetaResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response<T> {
    private MetaResponse meta;
    private T data;

    public Response(T data) {
        this.data = data;
        this.meta = MetaResponse.success();
    }

}
