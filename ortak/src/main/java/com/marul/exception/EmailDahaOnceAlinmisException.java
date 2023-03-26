package com.marul.exception;

public class EmailDahaOnceAlinmisException extends RuntimeException {
    public EmailDahaOnceAlinmisException(String s, Object... args) {
        super(String.format(s, args));
    }
}
