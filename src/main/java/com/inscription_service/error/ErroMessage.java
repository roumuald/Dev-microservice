package com.inscription_service.error;

public class ErroMessage extends RuntimeException{
    public ErroMessage(String message) {
        super(message);
    }
}
