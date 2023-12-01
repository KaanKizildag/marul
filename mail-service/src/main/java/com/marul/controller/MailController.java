package com.marul.controller;

import com.marul.dto.SendMailWithoutAttachmentDto;
import com.marul.dto.result.Result;
import com.marul.dto.result.SuccessDataResult;
import com.marul.service.EmailSenderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class MailController {

    private final EmailSenderService emailSenderService;

    public MailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public Result sendMail(@RequestBody @Valid SendMailWithoutAttachmentDto mailGondermeDto) {
        emailSenderService.sendMailWithoutAttachment(mailGondermeDto);
        return new SuccessDataResult<>("Mail başarıyla gönderildi.");
    }
}
