package com.marul.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailGondermeDto {
    private String emailTo;
    private String body;
    private String subject;
    private byte[] inputStream;
}
