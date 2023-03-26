package com.marul.exception;

public class ZatenKayitliException extends RuntimeException {
    public ZatenKayitliException(String mesaj, Object... args) {
        super(String.format(mesaj, args));
    }
}
