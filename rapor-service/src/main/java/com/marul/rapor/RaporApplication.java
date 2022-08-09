package com.marul.rapor;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, RaporApplication.class})
@EnableEurekaClient
public class RaporApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaporApplication.class, args);
    }
}
