package com.marul.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String mesaj, Object... args) {
        super(String.format(mesaj, args));
    }
}
