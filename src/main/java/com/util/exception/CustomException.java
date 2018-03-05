package com.util.exception;

public class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}