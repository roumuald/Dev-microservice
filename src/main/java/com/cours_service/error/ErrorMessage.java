package com.cours_service.error;

public class ErrorMessage extends RuntimeException{
    public ErrorMessage(String message) {
        super(message);
    }
}
