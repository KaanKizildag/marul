package com.marul.musteri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
public class MusteriApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusteriApplication.class, args);
    }
}
