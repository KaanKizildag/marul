package com.marul.stokservice.integration;

import com.marul.dto.urun.UrunDto;
import com.marul.util.MarulIntegration;
import org.springframework.stereotype.Component;

@Component
public class SatisServiceIntegration implements MarulIntegration {

    private final SatisFeignClient satisFeignClient;

    public SatisServiceIntegration(SatisFeignClient satisFeignClient) {
        this.satisFeignClient = satisFeignClient;
    }

    public UrunDto findUrunById(Long id) {
        return decode(() -> satisFeignClient.findUrunById(id));
    }

}
