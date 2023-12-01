package com.marul.emailsender;

import com.marul.dto.SendMailWithoutAttachmentDto;
import com.marul.service.EmailSenderService;
import com.marul.util.MailUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    @Disabled("mail göndermemesi için")
    void bodysiDoluOlanEmailGonderilebilmeli() throws IOException {
        Map<String, String> parametreMap = new HashMap<>();
        parametreMap.put("mesaj", "Marul mail-service test");
        parametreMap.put("adSoyad", "Örnek Gönderen Adı");
        String emailTo = "huseyinkaan.kizildag@gmail.com";
        String body = MailUtils.getMailBodyWithParameters("marul-mail-imza.html", parametreMap);
        String subject = "Marul mail-service test";

        URL url = Thread.currentThread().getContextClassLoader().getResource("test.pdf");
        File file = new File(url.getPath());

        try (InputStream inputStream = new FileInputStream(file)) {
            emailSenderService.sendMailWithAttachment(emailTo, subject, body, inputStream.readAllBytes());
        }
    }

    @SneakyThrows
    @Test
    @Disabled("mail göndermemesi için")
    void bodysiBosOlanEmailGonderilebilmeli() {

        String emailTo = "huseyinkaan.kizildag@gmail.com";
        String body = "marul projesi email test";
        String subject = "email-service test";
        SendMailWithoutAttachmentDto mailGondermeDto = new SendMailWithoutAttachmentDto();
        mailGondermeDto.setEmailTo(emailTo);
        mailGondermeDto.setBody(body);
        mailGondermeDto.setSubject(subject);
        emailSenderService.sendMailWithoutAttachment(mailGondermeDto);
    }
}
