package com.marul.exception;

public class ZatenKayitliException extends RuntimeException {
    public ZatenKayitliException(String mesaj, String... args) {
        super(String.format(mesaj, args));
    }
}
