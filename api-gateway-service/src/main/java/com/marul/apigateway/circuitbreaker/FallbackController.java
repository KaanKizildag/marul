package com.marul.apigateway.circuitbreaker;

import com.marul.dto.result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api-gateway")
@Slf4j
public class FallbackController {
    @GetMapping("/common-fallback")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult commonFallback(Exception exception) {
        log.error("api-gateway fallback method | {}", exception.getMessage());
        return new ErrorResult("servis ayakta değil.");
    }

}
