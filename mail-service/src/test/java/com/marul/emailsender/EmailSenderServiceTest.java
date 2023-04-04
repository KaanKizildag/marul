package com.marul.emailsender;

import com.marul.dto.mail.MailGondermeDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

//@SpringBootTest
class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @SneakyThrows
    @Test
    @Disabled("mail göndermemesi için")
    void bodysiDoluOlanEmailGonderilebilmeli() {

        String emailTo = "huseyinkaan.kizildag@gmail.com";
        String body = "marul projesi email test";
        String subject = "email-service test";

        URL url = Thread.currentThread().getContextClassLoader().getResource("test.pdf");
        File file = new File(url.getPath());

        InputStream inputStream = new FileInputStream(file);

        emailSenderService.sendMailWithAttachment(
                emailTo,
                body,
                subject,
                inputStream
        );
    }

    @SneakyThrows
    @Test
    @Disabled("mail göndermemesi için")
    void bodysiBosOlanEmailGonderilebilmeli() {

        String emailTo = "huseyinkaan.kizildag@gmail.com";
        String body = "marul projesi email test";
        String subject = "email-service test";
        MailGondermeDto mailGondermeDto = new MailGondermeDto();
        mailGondermeDto.setEmailTo(emailTo);
        mailGondermeDto.setBody(body);
        mailGondermeDto.setSubject(subject);
        emailSenderService.sendMailWithoutAttachment(mailGondermeDto);
    }
}
