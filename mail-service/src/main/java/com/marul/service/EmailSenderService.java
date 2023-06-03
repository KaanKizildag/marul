package com.marul.service;

import com.marul.config.EmailConfigData;
import com.marul.dto.SendMailWithoutAttachmentDto;
import com.marul.dto.mail.MailGondermeDto;
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

import static com.marul.kafka.MarulTopicNames.MARUL_MAIL;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final EmailConfigData emailConfigData;


    public void sendMailWithAttachment(String toEmail, String subject, String htmlBody, byte[] attachmentStream) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        String attachmentName = emailConfigData.getEkAdi();
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailConfigData.getFrom());
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlBody, true); // true parametresi HTML gövde olduğunu belirtir
            mimeMessageHelper.addAttachment(attachmentName, new ByteArrayResource(attachmentStream));
        } catch (MessagingException | IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new EmailGonderirkenException(emailConfigData.getBasarisizMesaj(), e.getMessage());
        }
        javaMailSender.send(mimeMessage);
        log.info(emailConfigData.getBasariliMesaj());
    }

    public void sendMailWithoutAttachment(SendMailWithoutAttachmentDto mailGondermeDto) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailConfigData.getFrom());
            mimeMessageHelper.setTo(mailGondermeDto.getEmailTo());
            mimeMessageHelper.setText(mailGondermeDto.getBody());
            mimeMessageHelper.setSubject(mailGondermeDto.getSubject());

        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new EmailGonderirkenException(emailConfigData.getBasarisizMesaj());
        }
        javaMailSender.send(mimeMessage);

    }

    @KafkaListener(topics = MARUL_MAIL, groupId = "group-id")
    public void sendMailWithAttachment(@Payload MailGondermeDto mailGondermeDto) {
        sendMailWithAttachment(mailGondermeDto.getEmailTo(),
                mailGondermeDto.getSubject(),
                mailGondermeDto.getBody(),
                mailGondermeDto.getInputStream());
    }
}
