package ru.practicum.main.exceptions;

public class ValidationRequestException extends RuntimeException {
    public ValidationRequestException(String message) {
        super(message);
    }
}