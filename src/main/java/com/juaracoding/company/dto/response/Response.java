package com.juaracoding.company.dto.response;

import lombok.Data;

@Data
public class Response<T> {

    private Boolean success;
    private String message;
    private T data;
}
