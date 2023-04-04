package com.marul.emailsender.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail-properties")
@Data
@RefreshScope
public class EmailConfigData {

    @Value("basariliMesaj")
    private String basariliMesaj;

    @Value("basarisizMesaj")
    private String basarisizMesaj;

    @Value("ekAdi")
    private String ekAdi;

    @Value("from")
    private String from;
}
