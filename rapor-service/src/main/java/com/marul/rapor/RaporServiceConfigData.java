package com.marul.rapor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rapor-service")
@Data
public class RaporServiceConfigData {
    private String raporlarDizini;
}
