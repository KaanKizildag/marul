package com.marul.util;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import com.marul.exception.ServisDonusHatasiException;


public interface ResultDecoder {


    static boolean utilServiceCheck(Result result) {
        return result.isSuccess();
    }

    static <T> T getDataResult(DataResult<T> result) {
        if (utilServiceCheck(result)) {
            return result.getData();
        }
        throw new ServisDonusHatasiException("servis dönüş hatası: %s", result.getMessage());
    }
}
