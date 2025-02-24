package com.example.GestioneEventi.exceptions;

public class BadRequestException extends Exception extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
