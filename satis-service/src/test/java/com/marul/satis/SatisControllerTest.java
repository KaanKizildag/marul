package com.marul.satis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.SatisDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.exception.BulunamadiException;
import com.marul.exception.GeneralExceptionHandler;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SatisControllerTest {

    @Mock
    private SatisService satisService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SatisController satisController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(satisController)
                .setControllerAdvice(new GeneralExceptionHandler())
                .build();
    }


    @SneakyThrows
    @Test
    void findAll() {
        SatisDto satisDto = getMockSatisDto();
        List<SatisDto> satisDtoList = Lists.list(satisDto);

        Mockito.when(satisService.findAll()).thenReturn(satisDtoList);

        mockMvc.perform(get("/v1/satis/findAll")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().string(Matchers.containsString("\"success\":true")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private SatisDto getMockSatisDto() {
        long urunId = 1L;
        long satisId = 1L;
        SatisDto satisDto = new SatisDto();

        MusteriDto musteriDto = new MusteriDto();
        long musteriId = 1L;
        String musteriAdi = "Kaan";


        satisDto.setMusteriId(musteriId);
        satisDto.setUrunId(urunId);
        satisDto.setId(satisId);
        return satisDto;
    }

    @SneakyThrows
    @Test
    void save() {
        SatisDto satisDto = getMockSatisDto();
//        Mockito.when(satisService.save(any()))
//                .thenReturn(Arrays.asList(satisDto));
        mockMvc.perform(post("/v1/satis/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(satisDto))
                )
                .andExpect(content().string(Matchers.containsString("\"success\":true")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void shouldNot_save_WhenProductIfNotExists() {
        SatisDto satisDto = getMockSatisDto();

//        Mockito.when(satisService.save(any()))
//                .thenThrow(new BulunamadiException("1 id ile ürün bulunamadı"));
        mockMvc.perform(post("/v1/satis/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(satisDto))
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BulunamadiException))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andDo(print());
    }
}