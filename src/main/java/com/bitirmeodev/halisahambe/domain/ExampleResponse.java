package com.bitirmeodev.halisahambe.domain;


public class ExampleResponse{
    private String data;

    public ExampleResponse(String data) {
        this.data = data;
    }

    public static ExampleResponse getInstance(String data){
        return new ExampleResponse(data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
