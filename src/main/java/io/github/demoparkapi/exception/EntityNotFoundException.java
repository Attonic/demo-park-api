package io.github.demoparkapi.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String format) {
        super(format);
    }
}
