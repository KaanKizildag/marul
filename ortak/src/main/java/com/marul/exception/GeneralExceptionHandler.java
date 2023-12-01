package com.marul.exception;

import com.marul.dto.result.ErrorDataResult;
import com.marul.dto.result.ErrorResult;
import com.marul.dto.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    private String appname;

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Result errorDataResult = new ErrorDataResult<>(errors, "Aşağıdaki alanlar hatalı gönderilmiştir." + errors);
        log.error("hatalı alanlar {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorDataResult);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result genelServisHatasi(RuntimeException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result genelServisHatasi(NotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result genelServisHatasi(AlreadyExistsException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(value = {ServisDonusHatasiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result genelServisHatasi(ServisDonusHatasiException exception) {
        log.error("Servis adı: {}", appname);
        return new ErrorResult(exception.getMessage());
    }
}
