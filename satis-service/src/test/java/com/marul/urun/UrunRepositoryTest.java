package com.marul.urun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UrunRepositoryTest {

    @Autowired
    private UrunRepository urunRepository;

    @Test
    void urunAdiIleKaydinvarOlupOlmadigiBulunabilmeli() {
        // given
        String urunAdi = "Marul";
        Urun urun = new Urun();
        urun.setUrunAdi(urunAdi);
        urun.setFiyat(BigDecimal.ZERO);
        urun.setKdv(0);

        urunRepository.save(urun);
        //when

        boolean expected = urunRepository.existsByUrunAdi(urunAdi);

        //then
        assertTrue(expected);
    }

}