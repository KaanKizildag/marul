package com.marul.apigateway.config;

import org.springframework.boot.system.SystemProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    private final String appnameMusteriService = SystemProperties.get("APPNAME_MUSTERI_SERVICE");
    private final String appnameSatisService = SystemProperties.get("APPNAME_SATIS_SERVICE");
    private final String appnameRaporService = SystemProperties.get("APPNAME_RAPOR_SERVICE");
    private final String appnameStokService = SystemProperties.get("APPNAME_STOK_SERVICE");
    private final String appnameMailService = SystemProperties.get("APPNAME_MAIL_SERVICE");
    private final String appnameApiGateway = SystemProperties.get("APPNAME_API_GATEWAY");

    private final String lb = "lb://";
    private final String forward = "forward://";
    private final String fallbackUrl = "/v1/api-gateway/common-fallback";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(appnameMusteriService, r -> r.path("/" + appnameMusteriService + "/**")
                        .filters(f -> f.circuitBreaker(c -> c.setFallbackUri(forward + appnameApiGateway + fallbackUrl)))
                        .uri(lb + appnameMusteriService))
                .route(appnameSatisService, r -> r.path("/" + appnameSatisService + "/**")
                        .filters(f -> f.circuitBreaker(c -> c.setFallbackUri(forward + appnameApiGateway + fallbackUrl)))
                        .uri(lb + appnameSatisService))
                .route(appnameMusteriService, r -> r.path("/" + appnameRaporService + "/**")
                        .filters(f -> f.circuitBreaker(c -> c.setFallbackUri(forward + appnameApiGateway + fallbackUrl)))
                        .uri(lb + appnameRaporService))
                .route(appnameMusteriService, r -> r.path("/" + appnameStokService + "/**")
                        .filters(f -> f.circuitBreaker(c -> c.setFallbackUri(forward + appnameApiGateway + fallbackUrl)))
                        .uri(lb + appnameStokService))
                .route(appnameMusteriService, r -> r.path("/" + appnameMailService + "/**")
                        .filters(f -> f.circuitBreaker(c -> c.setFallbackUri(forward + appnameApiGateway + fallbackUrl)))
                        .uri(lb + appnameMailService))
                .build();
    }
}
