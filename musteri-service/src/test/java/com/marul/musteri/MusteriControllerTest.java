package com.marul.musteri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.musteri.MusteriDto;
import com.marul.exception.GeneralExceptionHandler;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MusteriControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private MusteriService musteriService;
    @InjectMocks
    private MusteriController musteriController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(musteriController)
                .setControllerAdvice(new GeneralExceptionHandler())
                .build();
    }

    @Test
    void findAll() throws Exception {

        List<MusteriDto> musteriDtoList = Stream
                .of("Huseyin", "Kaan")
                .map(musteriAdi -> {
                    MusteriDto musteri = new MusteriDto();
                    musteri.setMusteriAdi(musteriAdi);
                    musteri.setTeslimatNoktasi("Çankaya");
                    musteri.setTelefonNo("00000");
                    musteri.setEmail(musteriAdi + "@marul.com.tr");
                    return musteri;
                })
                .collect(Collectors.toList());

        when(musteriService.findAll()).thenReturn(musteriDtoList);
        mockMvc.perform(get("/v1/musteri/findAll").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.TRUE)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {

        MusteriDto musteriDto = getMusteriDto();

        Long musteriId = getMusteriDto().getId();
        when(musteriService.findById(musteriId))
                .thenReturn(musteriDto);

        mockMvc.perform(get("/v1/musteri/findById?musteriId=" + musteriId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.TRUE)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testSaveMusteri() throws Exception {
        // Given
        MusteriDto musteriDto = getDto();

        when(musteriService.save(any(MusteriDto.class))).thenReturn(musteriDto);

        // When

        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(musteriDto.getId()))
                .andExpect(jsonPath("$.data.musteriAdi").value(musteriDto.getMusteriAdi()))
                .andExpect(jsonPath("$.data.telefonNo").value(musteriDto.getTelefonNo()))
                .andExpect(jsonPath("$.data.email").value(musteriDto.getEmail()))
                .andExpect(jsonPath("$.data.teslimatNoktasi").value(musteriDto.getTeslimatNoktasi()))
                .andExpect(jsonPath("$.data.borc").value(musteriDto.getBorc()))
                .andExpect(jsonPath("$.data.turId").value(musteriDto.getTurId()))
                .andExpect(jsonPath("$.data.turAdi").value(musteriDto.getTurAdi()))
                .andExpect(jsonPath("$.message").value("Müşteri başarıyla kaydedildi."))
                .andReturn();
    }

    @Test
    void testSaveMusteriWithInvalidMusteriAdi() throws Exception {
        MusteriDto musteriDto = getDto();
        musteriDto.setMusteriAdi("");
        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.musteriAdi").value("must not be blank"));
    }

    private MusteriDto getDto() {
        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setMusteriAdi("Ahmet Yılmaz");
        musteriDto.setTelefonNo("5321234567");
        musteriDto.setEmail("ahmet.yilmaz@gmail.com");
        musteriDto.setTeslimatNoktasi("İstanbul");
        musteriDto.setTurId(1L);
        musteriDto.setTurAdi("Bireysel");
        return musteriDto;
    }

    @Test
    void testSaveMusteriWithInvalidTelefonNo() throws Exception {
        MusteriDto musteriDto = getDto();
        musteriDto.setTelefonNo("+9123456");
        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.telefonNo").value("must match \"([0-9])*\""));
    }

    @Test
    void testSaveMusteriWithInvalidEmail() throws Exception {
        MusteriDto musteriDto = getDto();
        musteriDto.setEmail("ahmet.yilmaz.com");
        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.email").value("must be a well-formed email address"));
    }

    @Test
    void testSaveMusteriWithInvalidTeslimatNoktasi() throws Exception {
        MusteriDto musteriDto = getDto();
        musteriDto.setTeslimatNoktasi(null);
        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.teslimatNoktasi").value("must not be null"));
    }

    @Test
    void testSaveMusteriWithInvalidTurId() throws Exception {
        MusteriDto musteriDto = getDto();
        musteriDto.setTurId(null);
        mockMvc.perform(post("/v1/musteri/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.turId").value("must not be null"));
    }

    @Test
    void testDeleteMusteri() throws Exception {
        Long musteriId = 1L;
        doNothing().when(musteriService).deleteById(musteriId);

        mockMvc.perform(delete("/v1/musteri/delete?id=" + musteriId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Müşteri başarıyla silindi."));
    }

    @Test
    public void testUpdateMusteriWithInvalidData() throws Exception {
        MusteriDto musteriDto = getMusteriDto();
        musteriDto.setMusteriAdi("");
        mockMvc.perform(put("/v1/musteri/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.musteriAdi").value("must not be blank"));

        // Geçersiz Telefon Numarası
        musteriDto.setMusteriAdi("Ali");
        musteriDto.setTelefonNo("+9123456789");
        mockMvc.perform(put("/v1/musteri/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.telefonNo").value("must match \"([0-9])*\""));

        // Geçersiz E-posta Adresi
        musteriDto.setTelefonNo("5551234567");
        musteriDto.setEmail("invalid-email");
        mockMvc.perform(put("/v1/musteri/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.email").value("must be a well-formed email address"));

        // Geçersiz Teslimat Noktası
        musteriDto.setEmail("test@test.com");
        musteriDto.setTeslimatNoktasi(null);
        mockMvc.perform(put("/v1/musteri/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.teslimatNoktasi").value("must not be null"));


        // Geçersiz Tur ID
        musteriDto.setTeslimatNoktasi("Adana");
        musteriDto.setTurId(null);
        mockMvc.perform(put("/v1/musteri/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteriDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", Matchers.is(Boolean.FALSE)))
                .andExpect(jsonPath("$.data.turId").value("must not be null"));
    }

    private MusteriDto getMusteriDto() {
        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(1L);
        musteriDto.setTurId(1L);
        musteriDto.setTurAdi("Ankara");
        musteriDto.setMusteriAdi("musteri");
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setEmail("musteri@marul.com.tr");
        return musteriDto;
    }
}