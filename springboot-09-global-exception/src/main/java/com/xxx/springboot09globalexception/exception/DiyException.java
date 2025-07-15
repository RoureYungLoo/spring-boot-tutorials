package com.xxx.springboot09globalexception.exception;

public class DiyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public DiyException() {
    }

    public DiyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
