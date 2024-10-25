package com.utilisateur_service.errorMessage;

public class ErrorMessage extends RuntimeException{
    public ErrorMessage(String message) {
        super(message);
    }
}
