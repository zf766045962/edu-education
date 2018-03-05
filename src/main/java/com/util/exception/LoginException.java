package com.util.exception;

/**
 * @author adminchh
 */
public class LoginException extends Exception {
    private String message;


    public LoginException() {

    }

    public LoginException(String message) {
        super(message);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}