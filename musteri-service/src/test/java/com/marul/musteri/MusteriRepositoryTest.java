package com.marul.musteri;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class MusteriRepositoryTest {

    @Autowired
    private MusteriRepository musteriRepository;

    @Test
    void existsByEmail() {
        //given
        Musteri musteri = new Musteri();
        long turId = 1L;
        String email = "huseyinkaan.kizildag@gmail.com";
        double borc = 0D;
        musteri.setTelefonNo("00000");
        musteri.setTeslimatNoktasi("Kahramanmaraş");
        musteri.setBorc(borc);
        musteri.setMusteriAdi("Hüseyin Kaan");
        musteri.setEmail(email);
        musteri.setTurId(turId);
        musteriRepository.save(musteri);
        //when
        boolean actual = musteriRepository.existsByEmail(email);
        //then
        assertTrue(actual);
    }

    @Test
    void shoud_ReturnFalse_WhenEmailNotExists() {
        //given
        String email = "huseyinkaan.kizildag@gmail.com";
        //when
        boolean actual = musteriRepository.existsByEmail(email);
        //then
        assertFalse(actual);
    }

}