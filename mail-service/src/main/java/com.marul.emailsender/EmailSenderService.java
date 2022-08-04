package com.marul.emailsender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final EmailSenderConfigData emailSenderConfigData;

    public void sendMailWithAttachment(String toEmail,
                                       String body,
                                       String subject,
                                       InputStream attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("com.tr.marul@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        try {
            mimeMessageHelper.addAttachment(emailSenderConfigData.getEkAdi(), new ByteArrayResource(attachment.readAllBytes()));
        } catch (IOException e) {
            log.error(emailSenderConfigData.getBasarisizMesaj());
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
        log.info(emailSenderConfigData.getBasariliMesaj());
    }

    public void sendMailWithoutAttachment(String toEmail,
                                          String body,
                                          String subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("kaan10241024@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        javaMailSender.send(mimeMessage);
        log.info(emailSenderConfigData.getBasariliMesaj());
    }
}
