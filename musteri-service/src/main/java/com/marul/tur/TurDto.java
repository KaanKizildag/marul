package com.marul.tur;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurDto {
    private Long id;
    @NotNull
    private String turAdi;
}
