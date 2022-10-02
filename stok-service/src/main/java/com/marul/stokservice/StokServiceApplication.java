package com.marul.stokservice;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, StokServiceApplication.class})
@EnableEurekaClient
@EnableFeignClients
public class StokServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StokServiceApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
