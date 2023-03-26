package com.marul.exception;

public class ServisDonusHatasiException extends RuntimeException {
    public ServisDonusHatasiException(String s, Object... args) {
        super(String.format(s, args));
    }
}
