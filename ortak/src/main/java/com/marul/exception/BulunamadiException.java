package com.marul.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BulunamadiException extends RuntimeException {
    public BulunamadiException(String s, Object... args) {
        super(String.format(s, args));
    }
}
