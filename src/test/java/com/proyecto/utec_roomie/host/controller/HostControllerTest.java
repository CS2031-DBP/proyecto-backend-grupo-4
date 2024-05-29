package com.proyecto.utec_roomie.host.controller;


import com.proyecto.utec_roomie.config.JwtService;
import com.proyecto.utec_roomie.host.application.AnfitrionController;
import com.proyecto.utec_roomie.host.domain.AnfitrionService;
import com.proyecto.utec_roomie.host.dto.AnfitrionResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnfitrionController.class)
@Import(JwtService.TestConfig.class)
public class HostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnfitrionService anfitrionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAnfitrion() throws Exception {
        AnfitrionResponseDto anfitrionResponseDto = new AnfitrionResponseDto();
        // Set the properties of anfitrionResponseDto as needed

        when(anfitrionService.getAnfitrion(1L)).thenReturn(anfitrionResponseDto);

        mockMvc.perform(get("/anfitrion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Adjust jsonPath as per the properties of anfitrionResponseDto
    }

    @Test
    public void testActualizarAnfitrion() throws Exception {
        AnfitrionResponseDto anfitrionActualizacionDto = new AnfitrionResponseDto();
        // Set the properties of anfitrionActualizacionDto as needed

        mockMvc.perform(patch("/anfitrion/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"property\": \"value\" }")) // Adjust the JSON as per the properties of anfitrionActualizacionDto
                .andExpect(status().isOk());

        verify(anfitrionService, times(1)).actualizarAnfitrion(eq(1L), any(AnfitrionResponseDto.class));
    }

    @Test
    public void testEliminarAnfitrion() throws Exception {
        doNothing().when(anfitrionService).eliminarAnfitrion(1L);

        mockMvc.perform(delete("/anfitrion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(anfitrionService, times(1)).eliminarAnfitrion(1L);
    }
}
