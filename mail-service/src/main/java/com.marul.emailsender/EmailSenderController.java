package com.marul.emailsender;

import com.marul.dto.MailGondermeDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("v1/email-service")
@RequiredArgsConstructor
@Slf4j
public class EmailSenderController {

    private final EmailSenderService emailSenderService;
    private final EmailSenderConfigData emailSenderConfigData;

    @PostMapping("/email-gonder")
    public Result sendMailWithAttachment(@RequestBody MailGondermeDto mailGondermeDto) {

        emailSenderService.sendMailWithAttachment(mailGondermeDto.getEmailTo(),
                mailGondermeDto.getBody(),
                mailGondermeDto.getSubject(),
                new ByteArrayInputStream(mailGondermeDto.getInputStream()));

        log.info(emailSenderConfigData.getBasariliMesaj());
        return new SuccessResult(emailSenderConfigData.getBasariliMesaj());
    }
}
