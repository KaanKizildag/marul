package com.marul.dto.rapor;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RaporOlusturmaDto {
    private String raporAdi;
    private List<RaporDto> raporDtoList;
    private Map<String, Object> raporParametreleri;
}
