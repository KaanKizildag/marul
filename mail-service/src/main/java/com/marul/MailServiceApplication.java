package com.marul;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, MailServiceApplication.class})
@EnableDiscoveryClient
public class MailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }
}
