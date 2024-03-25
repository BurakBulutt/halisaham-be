package com.bitirmeodev.halisahambe.library.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DataResponse<T> {
    private List<T> items = List.of();

}
