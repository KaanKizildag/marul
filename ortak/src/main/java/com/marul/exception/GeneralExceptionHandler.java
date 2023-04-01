package com.marul.exception;

import com.marul.dto.result.ErrorResult;
import com.marul.dto.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    private String appname;

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result genelServisHatasi(RuntimeException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(value = {ServisDonusHatasiException.class})
    public Result genelServisHatasi(ServisDonusHatasiException exception) {
        return new ErrorResult(exception.getMessage());
    }
}
