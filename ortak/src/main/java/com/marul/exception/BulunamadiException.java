package com.marul.exception;

public class BulunamadiException extends RuntimeException {
    public BulunamadiException(String s, String... args) {
        super(String.format(s, args));
    }
}
