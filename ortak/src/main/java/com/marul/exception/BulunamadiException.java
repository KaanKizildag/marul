package com.marul.exception;

public class BulunamadiException extends RuntimeException {
    public BulunamadiException(String s, Object... args) {
        super(String.format(s, args));
    }
}
