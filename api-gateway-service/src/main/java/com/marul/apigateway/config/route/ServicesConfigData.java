package com.marul.apigateway.config.route;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "marul.service")
public class ServicesConfigData {

    private String musteriAppname;
    private String musteriPath;
    private String musteriUri;

    private String satisAppname;
    private String satisPath;
    private String satisUri;

    private String raporAppname;
    private String raporPath;
    private String raporUri;

    private String stokAppname;
    private String stokPath;
    private String stokUri;

    private String mailAppname;
    private String mailPath;
    private String mailUri;

    @Value("${marul.service.fallbackurl}")
    private String fallbackUrl;
}


