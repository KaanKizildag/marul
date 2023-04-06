package com.marul;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marul.dto.musteri.MusteriDto;
import com.marul.musteri.Musteri;
import com.marul.musteri.MusteriRepository;
import com.marul.musteri.MusteriService;
import com.marul.tur.Tur;
import com.marul.tur.TurDto;
import com.marul.tur.TurRepository;
import com.marul.tur.TurService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MusteriServiceIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MusteriRepository musteriRepository;
    @Autowired
    private TurRepository turRepository;
    @Autowired
    private MusteriService musteriService;
    @Autowired
    private TurService turService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void save_Returns400StatusCode_WhenEmailAlreadyTaken() throws Exception {
        Tur tur = new Tur("Kahramanmaraş");
        tur = turRepository.save(tur);
        Long turId = tur.getId();

        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setMusteriAdi("John");
        musteriDto.setTelefonNo("1234567890");
        musteriDto.setEmail("john@example.com");
        musteriDto.setTeslimatNoktasi(tur.getTurAdi());
        musteriDto.setBorc(0D);
        musteriDto.setTurId(turId);

        musteriService.save(musteriDto);

        mockMvc.perform(post("/v1/musteri/save")
                        .content(objectMapper.writeValueAsString(musteriDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                .andDo(print());
    }

    @Nested
    @DisplayName("Tur işlemleri test senaryoları")
    class TurTestleri {

        @BeforeEach
        void cleanUp() {
            turRepository.deleteAll();
        }

        @Test
        void turlarListelenebilmeli() throws Exception {

            List<Tur> turList = Arrays.asList(
                    new Tur("Kahramanmaraş"),
                    new Tur("Ankara"));
            turList = turRepository.saveAll(turList);

            mockMvc.perform(get("/v1/tur/findAll")
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.[0].id").value(turList.get(0).getId()))
                    .andExpect(jsonPath("$.data.[0].turAdi").value("Kahramanmaraş"))
                    .andExpect(jsonPath("$.data.[1].id").value(turList.get(1).getId()))
                    .andExpect(jsonPath("$.data.[1].turAdi").value("Ankara"))
                    .andDo(print());
        }

        @Test
        void turIdIleBulunabilmelidir() throws Exception {

            Tur tur = new Tur();
            tur.setTurAdi("Kahramanmaraş");
            tur = turRepository.save(tur);

            String urlTemplate = String.format("/v1/tur/findById/%d", tur.getId());
            mockMvc.perform(get(urlTemplate)
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.id").value(tur.getId()))
                    .andExpect(jsonPath("$.data.turAdi").value(tur.getTurAdi()))
                    .andDo(print());
        }

        @Test
        void turIdIleTurOlmadigindaHataVermelidir() throws Exception {

            String urlTemplate = String.format("/v1/tur/findById/%d", -1L);
            mockMvc.perform(get(urlTemplate)
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                    .andDo(print());
        }

        @Test
        void turKaydedilebilmelidir() throws Exception {

            TurDto tur = new TurDto();
            tur.setTurAdi("Kahramanmaraş");

            mockMvc.perform(post("/v1/tur/save")
                            .content(objectMapper.writeValueAsString(tur))
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.turAdi").value(tur.getTurAdi()))
                    .andDo(print());
        }

        @Test
        void turVarsaKaydedilmemelidir() throws Exception {

            TurDto tur = new TurDto();
            tur.setTurAdi("Kahramanmaraş");

            turService.save(tur);

            mockMvc.perform(post("/v1/tur/save")
                            .content(objectMapper.writeValueAsString(tur))
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("Müşteri işlemleri test senaryoları")
    class MusteriTestleri {

        @BeforeEach
        void cleanUp() {
            musteriRepository.deleteAll();
            turRepository.deleteAll();
        }

        @Test
        @DisplayName("Müşteriler listelenebilmelidir.")
        void musterilerListelenebilmelidir() throws Exception {
            Tur tur = new Tur("Kahramanmaraş");
            tur = turRepository.save(tur);
            Long turId = tur.getId();

            List<Musteri> musteriList = Arrays.asList(
                    new Musteri(5L, "müşteri1", "123123123", "musteri1@marul.com", "Ev", 0D, turId),
                    new Musteri(6L, "müşteri2", "123123123", "musteri2@marul.com", "İş", 15D, turId)
            );
            musteriRepository.saveAll(musteriList);

            mockMvc.perform(get("/v1/musteri/findAll")
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.[0].musteriAdi").value("müşteri1"))
                    .andExpect(jsonPath("$.data.[0].telefonNo").value("123123123"))
                    .andExpect(jsonPath("$.data.[0].email").value("musteri1@marul.com"))
                    .andExpect(jsonPath("$.data.[0].teslimatNoktasi").value("Ev"))
                    .andExpect(jsonPath("$.data.[0].borc").value(0D))
                    .andExpect(jsonPath("$.data.[0].turId").value(turId))

                    .andExpect(jsonPath("$.data.[1].musteriAdi").value("müşteri2"))
                    .andExpect(jsonPath("$.data.[1].telefonNo").value("123123123"))
                    .andExpect(jsonPath("$.data.[1].email").value("musteri2@marul.com"))
                    .andExpect(jsonPath("$.data.[1].teslimatNoktasi").value("İş"))
                    .andExpect(jsonPath("$.data.[1].borc").value(15D))
                    .andExpect(jsonPath("$.data.[1].turId").value(turId))
                    .andDo(print());
        }

        @Test
        void findById_ReturnsMusteriDto_WhenMusteriExists() throws Exception {
            Tur tur = new Tur("Kahramanmaraş");
            tur = turRepository.save(tur);
            Long turId = tur.getId();

            MusteriDto musteriDto = new MusteriDto();
            musteriDto.setMusteriAdi("John");
            musteriDto.setTelefonNo("1234567890");
            musteriDto.setEmail("john@example.com");
            musteriDto.setTeslimatNoktasi(tur.getTurAdi());
            musteriDto.setBorc(0D);
            musteriDto.setTurId(turId);
            musteriDto.setTurAdi("Regular");

            musteriDto = musteriService.save(musteriDto);

            // Act and Assert
            mockMvc.perform(get("/v1/musteri/findById")
                            .param("musteriId", musteriDto.getId().toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.musteriAdi", is(musteriDto.getMusteriAdi())))
                    .andExpect(jsonPath("$.data.telefonNo", is(musteriDto.getTelefonNo())))
                    .andExpect(jsonPath("$.data.email", is(musteriDto.getEmail())))
                    .andExpect(jsonPath("$.data.teslimatNoktasi", is(musteriDto.getTeslimatNoktasi())))
                    .andExpect(jsonPath("$.data.borc", is(musteriDto.getBorc())))
                    .andExpect(jsonPath("$.data.turId", is(musteriDto.getTurId().intValue())))
                    .andExpect(jsonPath("$.message", is("müşteri başariyla bulundu")));
        }

        @Test
        void findById_ReturnsNotFound_WhenMusteriDoesNotExist() throws Exception {
            // Arrange
            Long musteriId = -1L;

            // Act and Assert
            mockMvc.perform(get("/v1/musteri/findById")
                            .param("musteriId", musteriId.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message", is("-1 ile bir müşteri bulunamadı")));
        }

        @Test
        void save_ReturnsCustomerDto_WhenCustomerDtoIsValid() throws Exception {
            Tur tur = new Tur("Kahramanmaraş");
            tur = turRepository.save(tur);
            Long turId = tur.getId();

            MusteriDto musteriDto = new MusteriDto();
            musteriDto.setMusteriAdi("John");
            musteriDto.setTelefonNo("1234567890");
            musteriDto.setEmail("john@example.com");
            musteriDto.setTeslimatNoktasi(tur.getTurAdi());
            musteriDto.setBorc(0D);
            musteriDto.setTurId(turId);

            mockMvc.perform(post("/v1/musteri/save")
                            .content(objectMapper.writeValueAsString(musteriDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.id", Matchers.notNullValue()))
                    .andExpect(jsonPath("$.data.musteriAdi", is(musteriDto.getMusteriAdi())))
                    .andExpect(jsonPath("$.data.telefonNo", is(musteriDto.getTelefonNo())))
                    .andExpect(jsonPath("$.data.email", is(musteriDto.getEmail())))
                    .andExpect(jsonPath("$.data.teslimatNoktasi", is(musteriDto.getTeslimatNoktasi())))
                    .andExpect(jsonPath("$.data.borc", is(musteriDto.getBorc())))
                    .andExpect(jsonPath("$.data.turId", is(musteriDto.getTurId().intValue())))
                    .andDo(print());
        }

        @Test
        void save_ReturnsBadRequest_WhenCustomerDtoIsNotValid() throws Exception {
            Tur tur = new Tur("Kahramanmaraş");
            tur = turRepository.save(tur);
            Long turId = tur.getId();

            MusteriDto musteriDto = new MusteriDto();
            musteriDto.setMusteriAdi("");
            musteriDto.setTelefonNo("+904165");
            musteriDto.setEmail("johnexample.com");
            musteriDto.setTeslimatNoktasi(tur.getTurAdi());
            musteriDto.setBorc(0D);
            musteriDto.setTurId(turId);

            mockMvc.perform(post("/v1/musteri/save")
                            .content(objectMapper.writeValueAsString(musteriDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                    .andDo(print());
        }

        @Test
        void save_Returns404StatusCode_WhenTurIsNotExists() throws Exception {

            MusteriDto musteriDto = new MusteriDto();
            musteriDto.setMusteriAdi("John");
            musteriDto.setTelefonNo("1234567890");
            musteriDto.setEmail("john@example.com");
            musteriDto.setTeslimatNoktasi("İş");
            musteriDto.setBorc(0D);
            musteriDto.setTurId(-1L);

            mockMvc.perform(post("/v1/musteri/save")
                            .content(objectMapper.writeValueAsString(musteriDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.success", is(Boolean.FALSE)))
                    .andDo(print());
        }

        @Test
        void update_ReturnsOk200StatusCode_WhenCustomerDtoIsValid() throws Exception {
            Tur tur = new Tur("Kahramanmaraş");
            tur = turRepository.save(tur);
            Long turId = tur.getId();

            MusteriDto musteriDto = new MusteriDto();
            musteriDto.setMusteriAdi("John");
            musteriDto.setTelefonNo("1234567890");
            musteriDto.setEmail("john@example.com");
            musteriDto.setTeslimatNoktasi(tur.getTurAdi());
            musteriDto.setBorc(0D);
            musteriDto.setTurId(turId);
            musteriDto.setTurAdi("Regular");

            musteriDto = musteriService.save(musteriDto);

            musteriDto.setMusteriAdi("Kaan");

            mockMvc.perform(put("/v1/musteri/update")
                            .content(objectMapper.writeValueAsString(musteriDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.data.musteriAdi", is("Kaan")))
                    .andDo(print());
        }
    }


}
