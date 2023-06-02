package com.marul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail.properties")
@Data
@RefreshScope
public class EmailConfigData {

    private String basariliMesaj;

    private String basarisizMesaj;

    private String ekAdi;

    private String from;
}
