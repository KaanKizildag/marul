package com.marul.integration;

import com.marul.dto.musteri.MusteriDto;
import com.marul.util.MarulIntegration;
import org.springframework.stereotype.Component;

@Component
public class MusteriServiceIntegration implements MarulIntegration {
    private final MusteriFeignClient musteriFeignClient;

    public MusteriServiceIntegration(MusteriFeignClient musteriFeignClient) {
        this.musteriFeignClient = musteriFeignClient;
    }

    public boolean existsById(Long musteriId) {
        return decode(() -> musteriFeignClient.existsById(musteriId));
    }

    public MusteriDto findById(Long musteriId) {
        return decode(() -> musteriFeignClient.findById(musteriId));
    }

}
