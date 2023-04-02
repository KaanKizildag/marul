package com.marul.dto.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailGondermeDto {
    private String emailTo;
    private String body;
    private String subject;
    private byte[] inputStream;
}
