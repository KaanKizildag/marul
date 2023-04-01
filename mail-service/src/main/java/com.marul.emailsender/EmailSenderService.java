package com.marul.emailsender;

import com.marul.dto.mail.MailGondermeDto;
import com.marul.emailsender.config.EmailSenderConfigData;
import com.marul.exception.EmailGonderirkenException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

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
                                       InputStream attachment) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("com.tr.marul@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.addAttachment(emailSenderConfigData.getEkAdi(), new ByteArrayResource(attachment.readAllBytes()));
        } catch (MessagingException | IOException | IllegalArgumentException e) {
            log.error(emailSenderConfigData.getBasarisizMesaj());
            throw new EmailGonderirkenException(emailSenderConfigData.getBasarisizMesaj(), e.getMessage());
        }
        javaMailSender.send(mimeMessage);
        log.info(emailSenderConfigData.getBasariliMesaj());
    }

    @KafkaListener(
            topics = "marul-mail",
            groupId = "group-id"
    )
    public void sendMailWithoutAttachment(@Payload MailGondermeDto mailGondermeDto) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("com.tr.marul@gmail.com");
        mimeMessageHelper.setTo(mailGondermeDto.getEmailTo());
        mimeMessageHelper.setText(mailGondermeDto.getBody());
        mimeMessageHelper.setSubject(mailGondermeDto.getSubject());
        mimeMessageHelper.addAttachment(emailSenderConfigData.getEkAdi(), new ByteArrayResource(mailGondermeDto.getInputStream()));
        javaMailSender.send(mimeMessage);
        log.info(emailSenderConfigData.getBasariliMesaj());
    }
}
