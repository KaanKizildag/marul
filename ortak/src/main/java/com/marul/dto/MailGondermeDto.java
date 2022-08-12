package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailGondermeDto {
    private String emailTo;
    private String body;
    private String subject;
    private byte[] inputStream;
}
