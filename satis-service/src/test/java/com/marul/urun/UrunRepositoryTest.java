package com.marul.urun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class UrunRepositoryTest {

    @Autowired
    private UrunRepository urunRepository;

    @Test
    void urunAdiileUrunVarsaTrueDonmeli() {
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

    @Test
    void urunAdiileUrunYoksaFalseDonmeli() {
        // given
        String urunAdi = "Marul";

        //when
        boolean expected = urunRepository.existsByUrunAdi(urunAdi);

        //then
        assertFalse(expected);
    }

}