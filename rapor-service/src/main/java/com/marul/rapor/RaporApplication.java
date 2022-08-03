package com.marul.rapor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RaporApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaporApplication.class, args);
    }
}
