package com.marul.satis.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
