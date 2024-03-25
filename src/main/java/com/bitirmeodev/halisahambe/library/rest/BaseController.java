package com.bitirmeodev.halisahambe.library.rest;


import java.util.List;

public class BaseController {

    public <T> Response<DataResponse<T>> response(List<T> dataList){
        return new Response<>(new DataResponse<>(dataList));
    }

    public <T> Response<T> response(T data){
        return new Response<>(data);
    }

}
