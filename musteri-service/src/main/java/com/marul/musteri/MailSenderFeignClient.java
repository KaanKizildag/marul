package com.marul.musteri;

import com.marul.dto.mail.MailGondermeDto;
import com.marul.dto.result.SuccessResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mail-service")
@Deprecated
public interface MailSenderFeignClient {
    @PostMapping("mail-service/v1/email-service/email-gonder")
    SuccessResult sendMailWithAttachment(@RequestBody MailGondermeDto mailGondermeDto);
}
