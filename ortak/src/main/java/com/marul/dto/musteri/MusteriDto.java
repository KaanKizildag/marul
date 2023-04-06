package com.marul.dto.musteri;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusteriDto {

    private Long id;

    @NotBlank
    private String musteriAdi;

    @NotNull
    @Pattern(regexp = "([0-9])*")
    private String telefonNo;

    @Email
    private String email;
    @NotNull
    private String teslimatNoktasi;
    private Double borc = 0D;
    @NotNull
    private Long turId;
    private String turAdi;
}
