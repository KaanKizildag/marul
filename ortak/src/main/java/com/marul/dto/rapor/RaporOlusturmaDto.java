package com.marul.dto.rapor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RaporOlusturmaDto {
    @NotNull
    private String raporAdi;
    @NotEmpty
    private List<RaporDto> raporDtoList;
    @NotEmpty
    private Map<String, Object> raporParametreleri;
}
