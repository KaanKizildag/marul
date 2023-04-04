package com.marul.util;

import com.google.common.io.Resources;
import com.marul.exception.NotFoundException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface MailUtils {

    static String getMailBodyWithParameters(String dosyaAdi, Map<String, String> parametreMap) {
        URL url = Resources.getResource(dosyaAdi);
        String dosyaIcerigi = null;
        try {
            dosyaIcerigi = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new NotFoundException("%s dosya adı ile mail şablonu bulunamadı", dosyaAdi);
        }

        for (Map.Entry<String, String> entry : parametreMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            dosyaIcerigi = dosyaIcerigi.replace(":" + key, value);
        }

        return dosyaIcerigi;
    }
}
