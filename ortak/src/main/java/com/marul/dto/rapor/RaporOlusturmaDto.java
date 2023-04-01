package com.marul.dto.rapor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
