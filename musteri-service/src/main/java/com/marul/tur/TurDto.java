package com.marul.tur;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TurDto {
    private Long id;
    @NotNull
    private String turAdi;
}
