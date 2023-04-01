package com.marul.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServisDonusHatasiException extends RuntimeException {
    public ServisDonusHatasiException(String s, Object... args) {
        super(String.format(s, args));
    }
}
