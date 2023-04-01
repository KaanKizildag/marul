package com.marul.satis.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
public class SatisInsertDto {
    @NotNull
    private Long musteriId;
    @NotEmpty
    private List<@Valid SatisUrunDto> satisDtoList;
}
