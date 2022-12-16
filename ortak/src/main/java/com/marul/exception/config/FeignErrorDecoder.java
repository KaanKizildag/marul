package com.marul.exception.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.result.ErrorResult;
import com.marul.exception.ServisDonusHatasiException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResult errorResult;
        try {
            errorResult = objectMapper.readValue(response.body()
                            .asInputStream(),
                    ErrorResult.class);
            return new ServisDonusHatasiException(errorResult.getMessage());
        } catch (Exception eParam) {
            return new ServisDonusHatasiException("Servis dönüş hatası");
        }
    }
}
