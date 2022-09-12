package com.marul.musteri;

import com.marul.dto.MusteriDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MusteriControllerTest {

    @Mock
    private MusteriService musteriService;

    @InjectMocks
    private MusteriController musteriController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(musteriController).build();
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

        Mockito.when(musteriService.findAll()).thenReturn(musteriDtoList);
        ResultActions resultActions = mockMvc.perform(get("/v1/musteri/findAll").
                contentType(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(content().string(Matchers.containsString("\"success\":true")));
        resultActions
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {

        String musteriAdi = "Kaan";
        long musteriId = 1L;
        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(musteriId);
        musteriDto.setMusteriAdi(musteriAdi);
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setEmail(musteriAdi + "@marul.com.tr");

        Mockito.when(musteriService.findById(musteriId)).thenReturn(musteriDto);
        ResultActions resultActions = mockMvc.perform(get("/v1/musteri/findById/" + musteriId)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(content().string(Matchers.containsString("\"success\":true")));
        resultActions
                .andExpect(status().isOk());
    }
}