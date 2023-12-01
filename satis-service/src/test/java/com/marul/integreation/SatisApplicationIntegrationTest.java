package com.marul.integreation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.urun.BirimEnum;
import com.marul.dto.urun.UrunDto;
import com.marul.integration.StokServiceIntegration;
import com.marul.kategori.Kategori;
import com.marul.kategori.KategoriRepository;
import com.marul.satis.Satis;
import com.marul.satis.SatisRepository;
import com.marul.urun.Urun;
import com.marul.urun.UrunRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SatisApplicationIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private StokServiceIntegration stokServiceIntegration;

    @Autowired
    private UrunRepository urunRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private SatisRepository satisRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanUp() {
        urunRepository.deleteAll();
        kategoriRepository.deleteAll();
        satisRepository.deleteAll();
    }

    private UrunDto getMockUrunDto() {
        UrunDto urunDto = new UrunDto();
        urunDto.setUrunAdi("Eti Puf");
        urunDto.setKdv(18);
        urunDto.setFiyat(BigDecimal.valueOf(1.5));
        Kategori kategori = new Kategori();
        kategori.setKategoriAdi("Yiyecek");
        kategori = kategoriRepository.save(kategori);
        urunDto.setKategoriId(kategori.getId());
        return urunDto;
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

    @Test
    void idIleUrunSorgula() throws Exception {
        Urun urun = getMockUrun();
        urun = urunRepository.save(urun);

        mockMvc.perform(get("/v1/urun/findById?id=" + urun.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data.urunAdi", is("Eti Puf")))
                .andExpect(jsonPath("$.data.fiyat", is(1.5)))
                .andExpect(jsonPath("$.data.kdv", is(18)))
                .andExpect(jsonPath("$.data.kategoriAdi", is("Yiyecek")))
                .andDo(print());
    }


    @Test
    void urunSorgula() throws Exception {
        Urun urun = getMockUrun();
        urunRepository.save(urun);

        mockMvc.perform(get("/v1/urun/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data.[0].urunAdi", is("Eti Puf")))
                .andExpect(jsonPath("$.data.[0].fiyat", is(1.5)))
                .andExpect(jsonPath("$.data.[0].kdv", is(18)))
                .andExpect(jsonPath("$.data.[0].kategoriAdi", is("Yiyecek")))
                .andDo(print());
    }

    @Test
    void urunKaydedilebilmeli() throws Exception {

        UrunDto urunDto = getMockUrunDto();

        when(stokServiceIntegration.stokGuncelle(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(null);

        when(stokServiceIntegration.save(any()))
                .thenReturn(null);


        mockMvc.perform(post("/v1/urun/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urunDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data.fiyat", is(urunDto.getFiyat().doubleValue())))
                .andExpect(jsonPath("$.data.kdv", is(urunDto.getKdv())))
                .andExpect(jsonPath("$.data.kategoriId", is(urunDto.getKategoriId().intValue())))
                .andDo(print());
    }

    private Urun getUrun() {
        Urun urun = new Urun();
        urun.setFiyat(BigDecimal.ONE);
        urun.setUrunAdi("ürün adı");
        Kategori kategori = new Kategori();
        kategori.setKategoriAdi("Kategori");
        kategori = kategoriRepository.save(kategori);
        urun.setKategori(kategori);
        urun.setKdv(18);
        urun.setBirim(BirimEnum.KG);

        urun = urunRepository.save(urun);
        return urun;
    }

    @Test
    void haftalikSatislariGetir_ReturnsSalesMap_WhenRequestIsValid() throws Exception {
        Urun urun = getUrun();
        long musteriId = 1L;

        Satis satis = new Satis();
        satis.setSatisFiyati(urun.getFiyat());
        satis.setGrup_id(UUID.randomUUID());
        satis.setSatisZamani(LocalDateTime.now());
        satis.setSatilanAdet(10L);
        satis.setMusteriId(musteriId);
        satis.setUrunId(urun.getId());

        satisRepository.save(satis);

        mockMvc.perform(get("/v1/satis/onceki-haftaya-gore-satis-dustu-mu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data", Matchers.aMapWithSize(1)))
                .andDo(print());

    }
}
