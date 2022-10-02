package com.marul;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, SatisApplication.class})
@EnableEurekaClient
@EnableFeignClients
public class SatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SatisApplication.class, args);
    }
}
