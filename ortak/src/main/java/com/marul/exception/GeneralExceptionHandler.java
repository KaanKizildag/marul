package com.marul.exception;

import com.marul.dto.MusteriEklemeResponse;
import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

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
//
@ExceptionHandler(EmailDahaOnceAlinmisException.class)
public ResponseEntity<Result> emailDahaOnceAlinmisExceptionHandler(EmailDahaOnceAlinmisException exception) {
    MusteriEklemeResponse musteriEklemeResponse = new MusteriEklemeResponse();
    musteriEklemeResponse.setCevapMesaji(exception.getMessage());

    DataResult<MusteriEklemeResponse> dataResult = new DataResult<>();
    dataResult.setData(musteriEklemeResponse);
    dataResult.setSuccess(false);
    dataResult.setMessage(exception.getMessage());

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(dataResult);
}
//
//    @ExceptionHandler(RaporOlusturmaException.class)
//    public ResponseEntity<Result> raporOlustururkenHata(RaporOlusturmaException exception) {
//
//        Result result = new Result();
//        result.setMessage(exception.getMessage());
//        result.setSuccess(false);
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(result);
//    }
//
////    @ExceptionHandler(TurZatenKayitliException.class)
////    public ResponseEntity<TurResponse> turZatenKayitliExceptionHandler(TurZatenKayitliException exception) {
////        TurResponse musteriEklemeResponse = new TurResponse();
////        musteriEklemeResponse.setCevapMesaji(exception.getMessage());
////        musteriEklemeResponse.setIslemTarihi(LocalDateTime.now());
////        musteriEklemeResponse.setIslemSonucu(IslemSonucuEnum.BASARISIZ);
////        return new ResponseEntity<>(musteriEklemeResponse, HttpStatus.NOT_ACCEPTABLE);
////    }
}
