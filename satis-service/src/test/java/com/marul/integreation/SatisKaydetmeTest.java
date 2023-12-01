package com.marul.integreation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.mail.MailGondermeDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.urun.BirimEnum;
import com.marul.exception.ServisDonusHatasiException;
import com.marul.exception.YeterliStokYokException;
import com.marul.integration.MusteriServiceIntegration;
import com.marul.integration.RaporServiceIntegration;
import com.marul.integration.StokServiceIntegration;
import com.marul.kategori.Kategori;
import com.marul.kategori.KategoriRepository;
import com.marul.satis.Satis;
import com.marul.satis.SatisRepository;
import com.marul.satis.dto.SatisInsertDto;
import com.marul.satis.dto.SatisUrunDto;
import com.marul.urun.Urun;
import com.marul.urun.UrunRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SatisKaydetmeTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UrunRepository urunRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private SatisRepository satisRepository;

    @MockBean
    private MusteriServiceIntegration musteriServiceIntegration;

    @MockBean
    private StokServiceIntegration stokServiceIntegration;

    @MockBean
    private RaporServiceIntegration raporServiceIntegration;

    @MockBean
    private KafkaTemplate<String, SatisInsertDto> kafkaTemplate;

    @MockBean
    private KafkaTemplate<String, MailGondermeDto> mailKafkaTemplate;

    @Autowired
    private MockMvc mockMvc;

    public static Stream<RuntimeException> stokServiceExceptions() {
        return Stream.of(new ServisDonusHatasiException("Stok servis ayakta değil"),
                new YeterliStokYokException("Yeterli stok yok")
        );
    }

    @BeforeEach
    void cleanUp() {
        urunRepository.deleteAll();
        kategoriRepository.deleteAll();
        satisRepository.deleteAll();
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
    void save_ReturnsOk200StatusCode_WhenValidRequest() throws Exception {
        long musteriId = 1L;
        Urun urun = getUrun();

        MusteriDto musteriDto = getMusteriDto();

        SatisInsertDto satisInsertDto = getSatisInsertDto(musteriId, urun);

        when(musteriServiceIntegration.existsById(musteriId))
                .thenReturn(true);

        when(musteriServiceIntegration.findById(musteriId))
                .thenReturn(musteriDto);

        when(stokServiceIntegration.stokGuncelle(anyLong(), anyLong()))
                .thenReturn(true);

        when(raporServiceIntegration.generateSimpleReport(any()))
                .thenReturn(any());

        mockMvc.perform(post("/v1/satis/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(satisInsertDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(Boolean.TRUE))).andDo(print());

        verify(kafkaTemplate, Mockito.times(1))
                .send(anyString(), any());

        verify(mailKafkaTemplate, Mockito.times(1))
                .send(anyString(), any());
    }

    private SatisInsertDto getSatisInsertDto(long musteriId, Urun urun) {
        SatisInsertDto satisInsertDto = new SatisInsertDto();
        satisInsertDto.setMusteriId(musteriId);
        SatisUrunDto satisUrunDto = new SatisUrunDto();
        satisUrunDto.setUrunId(urun.getId());
        satisUrunDto.setSatilanAdet(10L);
        satisInsertDto.setSatisDtoList(Collections.singletonList(satisUrunDto));
        return satisInsertDto;
    }

    private MusteriDto getMusteriDto() {
        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setMusteriAdi("musteri adı");
        musteriDto.setBorc(0D);
        return musteriDto;
    }

    @Test
    void save_ReturnsInternalServiceError_WhenCustomerServiceIsDown() throws Exception {
        long musteriId = 1L;
        Urun urun = getUrun();

        getMusteriDto();

        SatisInsertDto satisInsertDto = getSatisInsertDto(musteriId, urun);

        when(musteriServiceIntegration.existsById(musteriId))
                .thenThrow(new ServisDonusHatasiException("Servis dönüş hatası"));

        when(musteriServiceIntegration.findById(musteriId))
                .thenThrow(new ServisDonusHatasiException("Servis dönüş hatası"));

        mockMvc.perform(post("/v1/satis/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(satisInsertDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success", is(Boolean.FALSE))).andDo(print());

        verify(kafkaTemplate, Mockito.times(0))
                .send(anyString(), any());

        verify(mailKafkaTemplate, Mockito.times(0))
                .send(anyString(), any());

        verify(raporServiceIntegration, Mockito.times(0))
                .generateSimpleReport(any());

        verify(stokServiceIntegration, Mockito.times(0))
                .stokGuncelle(anyLong(), anyLong());

        satisKaydiOlusmamali();
    }

    @ParameterizedTest
    @MethodSource("stokServiceExceptions")
    void save_ReturnsInternalServiceError_WhenStockServiceIsDown(RuntimeException stockServiceException) throws Exception {
        long musteriId = 1L;
        Urun urun = getUrun();

        MusteriDto musteriDto = getMusteriDto();

        SatisInsertDto satisInsertDto = getSatisInsertDto(musteriId, urun);

        when(musteriServiceIntegration.existsById(musteriId)).thenReturn(true);

        when(musteriServiceIntegration.findById(musteriId)).thenReturn(musteriDto);

        when(stokServiceIntegration.stokGuncelle(anyLong(), anyLong()))
                .thenThrow(stockServiceException);

        mockMvc.perform(post("/v1/satis/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(satisInsertDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                .andDo(print());

        verify(kafkaTemplate, Mockito.times(0)).send(anyString(), any());

        verify(mailKafkaTemplate, Mockito.times(0)).send(anyString(), any());

        verify(raporServiceIntegration, Mockito.times(0)).generateSimpleReport(any());

        verify(stokServiceIntegration, Mockito.times(1)).stokGuncelle(anyLong(), anyLong());

        verify(musteriServiceIntegration, Mockito.times(1)).existsById(anyLong());

        verify(musteriServiceIntegration, Mockito.times(0)).findById(anyLong());

        satisKaydiOlusmamali();
    }

    public void satisKaydiOlusmamali() {
        List<Satis> all = satisRepository.findAll();
        Assertions.assertTrue(all.isEmpty());
    }


}
