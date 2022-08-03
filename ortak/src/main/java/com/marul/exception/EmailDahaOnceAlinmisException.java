package com.marul.exception;

public class EmailDahaOnceAlinmisException extends RuntimeException {
    public EmailDahaOnceAlinmisException(String s, String... args) {
        super(String.format(s, args));
    }
}
