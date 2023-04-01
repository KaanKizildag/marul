package com.marul;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, SatisApplication.class})
@EnableDiscoveryClient
@EnableFeignClients
public class SatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SatisApplication.class, args);
    }
}
