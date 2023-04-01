package com.marul.emailsender;

import com.marul.exception.GeneralExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackageClasses = {GeneralExceptionHandler.class, MailSenderApplication.class})
@EnableDiscoveryClient
public class MailSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);
    }
}
