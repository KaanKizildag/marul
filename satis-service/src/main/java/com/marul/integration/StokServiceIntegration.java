package com.marul.integration;

import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.util.MarulIntegration;
import org.springframework.stereotype.Component;

@Component
public class StokServiceIntegration implements MarulIntegration {

    private final StokFeignClient stokFeignClient;

    public StokServiceIntegration(StokFeignClient stokFeignClient) {
        this.stokFeignClient = stokFeignClient;
    }

    public StokDto save(StokKaydetDto stokKaydetDto) {
        return decode(() -> stokFeignClient.save(stokKaydetDto));
    }

    public Boolean stokGuncelle(Long urunId, Long stok) {
        return decode(() -> stokFeignClient.stokGuncelle(urunId, stok));
    }

    public void stokSil(Long urunId) {
        utilServiceCheck(() -> stokFeignClient.stokSil(urunId));
    }

}
