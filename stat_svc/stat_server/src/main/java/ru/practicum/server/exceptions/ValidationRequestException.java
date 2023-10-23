package ru.practicum.server.exceptions;

public class ValidationRequestException extends RuntimeException {

    public ValidationRequestException(String message) {
        super(message);
    }
}