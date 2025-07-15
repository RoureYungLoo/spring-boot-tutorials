package com.xxx.springboot19commonprojectarchitect.exception;

import lombok.Getter;

@Getter
public class BusinessErrorException extends RuntimeException {
    private int code;
    private String message;

    public BusinessErrorException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
