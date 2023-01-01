package com.marul.musteri.integration;

import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.util.MarulIntegration;
import org.springframework.stereotype.Component;

@Component
public class RaporServiceIntegration implements MarulIntegration {

    private final RaporServiceFeignClient raporServiceFeignClient;

    public RaporServiceIntegration(RaporServiceFeignClient raporServiceFeignClient) {
        this.raporServiceFeignClient = raporServiceFeignClient;
    }

    public byte[] generateSimpleReport(RaporOlusturmaDto raporOlusturmaDto) {
        return decode(() -> raporServiceFeignClient.generateSimpleReport(raporOlusturmaDto));
    }

}
