package com.marul.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailWithoutAttachmentDto {
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
}
