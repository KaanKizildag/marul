package com.marul;

import com.marul.kategori.Kategori;
import com.marul.kategori.KategoriRepository;
import com.marul.urun.Urun;
import com.marul.urun.UrunRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SatisApplicationIntegrationTest {

    @Autowired
    private UrunRepository urunRepository;
    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanUp() {
        urunRepository.deleteAll();
        kategoriRepository.deleteAll();
    }

    @SneakyThrows
    @Test
    @DisplayName("ürün sorgulanabilmeli")
    void urunSorgula() {
        Urun urun = getMockUrun();
        urunRepository.save(urun);

        mockMvc.perform(get("/v1/urun/findAll")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data.[0].urunAdi", is("Eti Puf")))
                .andExpect(jsonPath("$.data.[0].fiyat", is(1.5)))
                .andExpect(jsonPath("$.data.[0].kdv", is(18)))
                .andExpect(jsonPath("$.data.[0].kategoriAdi", is("Yiyecek")))
                .andDo(print());
    }

    private Urun getMockUrun() {
        Urun urun = new Urun();
        urun.setUrunAdi("Eti Puf");
        urun.setKdv(18);
        urun.setFiyat(BigDecimal.valueOf(1.5));
        Kategori kategori = new Kategori();
        kategori.setKategoriAdi("Yiyecek");
        kategoriRepository.save(kategori);
        urun.setKategori(kategori);
        return urun;
    }

}
