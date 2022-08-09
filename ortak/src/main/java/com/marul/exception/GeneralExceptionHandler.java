package com.marul.exception;

import com.marul.dto.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(EmailDahaOnceAlinmisException.class)
    public ResponseEntity<Result> emailDahaOnceAlinmisExceptionHandler(EmailDahaOnceAlinmisException exception) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result);
    }

    @ExceptionHandler(RaporOlusturmaException.class)
    public ResponseEntity<Result> raporOlustururkenHata(RaporOlusturmaException exception) {
        Result result = new Result();
        result.setMessage(exception.getMessage());
        result.setSuccess(false);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result);
    }
}
