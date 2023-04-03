package com.marul.stokservice.stok;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class StokRepositoryTest {

    @Autowired
    private StokRepository stokRepository;

    @Test
    void yeterliStokVarMi() {
        //given
        Stok stok = new Stok();
        long adet = 10L;
        stok.setAdet(adet);
        long urunId = 1L;
        stok.setUrunId(urunId);
        stokRepository.save(stok);
        //when
        Boolean actual = stokRepository.yeterliStokVarMi(urunId, adet - 1);
        //then
        assertTrue(actual);

    }

    @Test
    void yeterliStokOlmamasiDurumu() {
        //given
        Stok stok = new Stok();
        long adet = 10L;
        stok.setAdet(adet);
        long urunId = 1L;
        stok.setUrunId(urunId);
        stokRepository.save(stok);
        //when
        Boolean actual = stokRepository.yeterliStokVarMi(urunId, adet + 1);
        //then
        assertFalse(actual);
    }

    @Test
    void tamYeterliStokOlmasiDurumu() {
        //given
        Stok stok = new Stok();
        long adet = 10L;
        stok.setAdet(adet);
        long urunId = 1L;
        stok.setUrunId(urunId);
        stokRepository.save(stok);
        //when
        Boolean actual = stokRepository.yeterliStokVarMi(urunId, adet);
        //then
        assertTrue(actual);
    }

    @Test
    void findByUrunId() {
        //given
        Stok stok = new Stok();
        stok.setAdet(10L);
        long urunId = 1L;
        stok.setUrunId(urunId);
        stokRepository.save(stok);
        //when
        Optional<Stok> optionalStok = stokRepository.findByUrunId(urunId);
        //then
        assertTrue(optionalStok.isPresent());
    }
}