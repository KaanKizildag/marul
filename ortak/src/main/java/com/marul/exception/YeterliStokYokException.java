package com.marul.exception;

public class YeterliStokYokException extends RuntimeException {
    public YeterliStokYokException(String s, Object... args) {
        super(String.format(s, args));
    }
}
