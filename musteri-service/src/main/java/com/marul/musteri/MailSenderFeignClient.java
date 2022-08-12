package com.marul.musteri;

import com.marul.dto.MailGondermeDto;
import com.marul.dto.result.SuccessResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mail-service")
public interface MailSenderFeignClient {
    @PostMapping("v1/email-service/email-gonder")
    SuccessResult sendMailWithAttachment(@RequestBody MailGondermeDto mailGondermeDto);
}
