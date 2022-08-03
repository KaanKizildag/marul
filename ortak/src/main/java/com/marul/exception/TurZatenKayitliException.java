package com.marul.exception;

public class TurZatenKayitliException extends RuntimeException {
    public TurZatenKayitliException(String mesaj, String... args) {
        super(String.format(mesaj, args));
    }
}
