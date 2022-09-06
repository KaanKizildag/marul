package com.marul.dto;

import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;
//import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MusteriDto {

    private Long id;
    //    @NotNull
    private String musteriAdi;

//    @NotNull
//    @Pattern(regexp = "([0-9])*")
    private String telefonNo;

//    @NotNull
    private String email;
//    @NotNull
    private String teslimatNoktasi;
    private Double borc = 0D;
//    @NotNull
    private Long turId;
}
