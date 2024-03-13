package com.example.On_class.domain.model.exception;

public class CharacterLengthException extends RuntimeException{
    public CharacterLengthException(String message) {
        super(message);
    }
}
