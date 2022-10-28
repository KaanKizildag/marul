package com.marul.emailsender.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email-sender")
@Data
public class EmailSenderConfigData {
    @Value("basariliMesaj")
    private String basariliMesaj;

    @Value("basarisizMesaj")
    private String basarisizMesaj;

    @Value("ekAdi")
    private String ekAdi;
}
