package com.marul.rapor;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, RaporApplication.class})
@EnableDiscoveryClient
public class RaporApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaporApplication.class, args);
    }
}
