package com.marul.exception;

import com.marul.dto.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler /*extends ResponseEntityExceptionHandler*/ {


//    @NotNull
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  @NotNull HttpHeaders headers,
//                                                                  @NotNull HttpStatus status,
//                                                                  @NotNull WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = {RuntimeException.class, RaporOlusturmaException.class,
            EmailGonderirkenException.class,
            EmailDahaOnceAlinmisException.class,
            BulunamadiException.class,
            ServisDonusHatasiException.class
    })
    public Result genelServisHatasi(RuntimeException exception) {
        return new Result(false, exception.getMessage());
    }
}
