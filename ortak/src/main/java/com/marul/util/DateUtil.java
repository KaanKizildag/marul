package com.marul.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface DateUtil {

    static Date dateFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
