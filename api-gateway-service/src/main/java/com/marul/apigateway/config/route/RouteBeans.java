package com.marul.apigateway.config.route;

import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class RouteBeans {

    private static final String CIRCUIT_BREAKER_NAME = "circuit-breaker";
    private final ServicesConfigData servicesConfigData;

    public RouteBeans(ServicesConfigData servicesConfigData) {
        this.servicesConfigData = servicesConfigData;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(servicesConfigData.getMusteriAppname(),
                        r -> r.path(servicesConfigData.getMusteriPath())
                                .filters(f -> f.circuitBreaker(circuitBreakerConfig(servicesConfigData.getFallbackUrl())))
                                .uri(servicesConfigData.getMusteriUri()))
                .route(servicesConfigData.getRaporAppname(),
                        r -> r.path(servicesConfigData.getRaporPath())
                                .filters(f -> f.circuitBreaker(circuitBreakerConfig(servicesConfigData.getFallbackUrl())))
                                .uri(servicesConfigData.getRaporUri()))
                .route(servicesConfigData.getSatisAppname(),
                        r -> r.path(servicesConfigData.getSatisPath())
                                .filters(f -> f.circuitBreaker(circuitBreakerConfig(servicesConfigData.getFallbackUrl())))
                                .uri(servicesConfigData.getSatisUri()))
                .route(servicesConfigData.getStokAppname(),
                        r -> r.path(servicesConfigData.getStokPath())
                                .filters(f -> f.circuitBreaker(circuitBreakerConfig(servicesConfigData.getFallbackUrl())))
                                .uri(servicesConfigData.getStokUri()))
                .route(servicesConfigData.getMailAppname(),
                        r -> r.path(servicesConfigData.getMailPath())
                                .filters(f -> f.circuitBreaker(circuitBreakerConfig(servicesConfigData.getFallbackUrl())))
                                .uri(servicesConfigData.getMailUri()))
                .build();
    }

    private Consumer<SpringCloudCircuitBreakerFilterFactory.Config> circuitBreakerConfig(String fallbackUrl) {
        return config -> config.setFallbackUri(fallbackUrl)
                .setName(CIRCUIT_BREAKER_NAME);
    }
}
