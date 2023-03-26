package com.marul.exception;

public class RaporOlusturmaException extends RuntimeException {
    public RaporOlusturmaException(String s, Object... args) {
        super(String.format(s, args));
    }
}
