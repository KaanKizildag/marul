package com.marul.stokservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StokServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StokServiceApplication.class, args);
    }
}
