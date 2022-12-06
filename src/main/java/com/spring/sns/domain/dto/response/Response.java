package com.spring.sns.domain.dto.response;

public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> error(String errorCode) {

    }
}
