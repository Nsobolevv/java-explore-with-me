package ru.practicum.main.exceptions;

public class ForbiddenArgumentException extends RuntimeException {
    public ForbiddenArgumentException(String message) {
        super(message);
    }
}