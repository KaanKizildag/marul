package com.marul.exception;

import java.util.Arrays;

public class EmailGonderirkenException extends RuntimeException {
    public EmailGonderirkenException(String s, String... args) {
        super(s + ": " + Arrays.toString(args));
    }
}
