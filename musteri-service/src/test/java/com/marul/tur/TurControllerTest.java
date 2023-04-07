package com.marul.tur;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TurControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TurService turService;

    @InjectMocks
    private TurController turController;

    private List<TurDto> turList;

    private TurDto turDto;

    @BeforeEach
    public void setUp() {
        turList = new ArrayList<>();
        turDto = new TurDto();
        turDto.setId(1L);
        turDto.setTurAdi("Tur1");
        turList.add(turDto);
        mockMvc = MockMvcBuilders.standaloneSetup(turController).build();
    }

    @Test
    void findAllTest() throws Exception {
        when(turService.findAll()).thenReturn(turList);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tur/findAll"))
                .andExpect(status().isOk())
                .andReturn();

        verify(turService, times(1)).findAll();
    }

    @Test
    void findByIdTest() throws Exception {
        when(turService.findById(any())).thenReturn(turDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tur/findById/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();

        verify(turService, times(1)).findById(anyLong());
    }

    @Test
    void saveTest() throws Exception {
        String turDtoJson = objectMapper.writeValueAsString(turDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/tur/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(turDtoJson))
                .andExpect(status().isOk());

        // verify the service method was called
        verify(turService, times(1)).save(any());
    }
}
