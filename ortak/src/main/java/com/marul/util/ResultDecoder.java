package com.marul.util;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import com.marul.exception.ServisDonusHatasiException;


public interface ResultDecoder {

    static void utilServiceCheck(Result result) {
        if (!result.isSuccess()) {
            throw new ServisDonusHatasiException("servis dönüş hatası: %s", result.getMessage());
        }
    }

    static <T> T getDataResult(DataResult<T> result) {
        utilServiceCheck(result);
        return result.getData();
    }
}
