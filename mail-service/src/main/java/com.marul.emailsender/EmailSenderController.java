package com.marul.emailsender;

import com.marul.dto.result.DataResult;
import com.marul.dto.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;


@RestController
@RequestMapping("v1/email-service")
@RequiredArgsConstructor
@Slf4j
public class EmailSenderController {

    private final EmailSenderService emailSenderService;
    private final EmailSenderConfigData emailSenderConfigData;

    @PostMapping("/email/{emailTo}-{body}-{subject}")
    @SneakyThrows // todo exception handler yapisi tasininca kaldirilicak.
    public Result sendMailWithAttachment(@PathVariable String emailTo,
                                         @PathVariable String body,
                                         @PathVariable String subject,
                                         @RequestBody InputStream inputStream
    ) {
        emailSenderService.sendMailWithAttachment(emailTo, body, subject, inputStream);
        DataResult<String> dataResult = new DataResult<>();
        log.info(emailSenderConfigData.getBasariliMesaj());
        dataResult.setMessage(emailSenderConfigData.getBasariliMesaj());
        return dataResult;
    }
}
