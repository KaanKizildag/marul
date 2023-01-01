package com.marul.util;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;

import java.util.function.Supplier;

public interface MarulIntegration {

    default <T> T decode(Supplier<DataResult<T>> supplier) {
        DataResult<T> dataResult = supplier.get();
        return ResultDecoder.getDataResult(dataResult);
    }

    default void utilServiceCheck(Supplier<Result> supplier) {
        Result result = supplier.get();
        ResultDecoder.utilServiceCheck(result);
    }
}
