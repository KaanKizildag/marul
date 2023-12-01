package com.marul.tur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class TurRepositoryTest {

    @Autowired
    private TurRepository turRepository;

    @Test
    void kayitliTurAdiIleTurBulunabilmeli() {
        // given
        Tur tur = new Tur();
        String turAdi = "kahramanmaraş";
        tur.setTurAdi(turAdi);

        turRepository.save(tur);
        // when
        boolean expected = turRepository.existsByTurAdi(turAdi);
        //then
        assertTrue(expected);
    }

    @Test
    void kayitliOlmayanTurAdiIleTurBulunamamali() {
        // given
        String turAdi = "kahramanmaraş";
        // when
        boolean expected = turRepository.existsByTurAdi(turAdi);
        //then
        assertFalse(expected);
    }
}