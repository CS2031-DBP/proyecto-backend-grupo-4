package com.proyecto.utec_roomie.publication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.utec_roomie.config.JwtService;
import com.proyecto.utec_roomie.publication.application.PublicacionController;
import com.proyecto.utec_roomie.publication.domain.PublicacionService;
import com.proyecto.utec_roomie.publication.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.publication.dto.PublicacionResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = PublicacionController.class)
public class publicacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacionService publicacionService;

    @MockBean
    private JwtService jwtService;  // Mockeando JwtService

    @Autowired
    private ObjectMapper objectMapper;

    private PublicacionRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new PublicacionRequestDto();
        requestDto.setTitulo("Test Titulo");
        requestDto.setDescripcion("Test Descripcion");
    }

    @Test
    void testCrearPublicacion() throws Exception {
        Mockito.when(publicacionService.crearPublicacion(Mockito.any(PublicacionRequestDto.class)))
                .thenReturn("/publicaciones/1");

        mockMvc.perform(post("/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testListarPublicaciones() throws Exception {
        PublicacionResponseDto responseDto = new PublicacionResponseDto();
        responseDto.setTitulo("Test Titulo");
        responseDto.setDescripcion("Test Descripcion");

        Mockito.when(publicacionService.getPublicaciones())
                .thenReturn(Collections.singletonList(responseDto));

        mockMvc.perform(get("/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Test Titulo"))
                .andExpect(jsonPath("$[0].descripcion").value("Test Descripcion"));
    }

    @Test
    void testEliminarPublicacion() throws Exception {
        mockMvc.perform(delete("/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePublicacion() throws Exception {
        PublicacionResponseDto responseDto = new PublicacionResponseDto();
        responseDto.setTitulo("Updated Titulo");
        responseDto.setDescripcion("Updated Descripcion");

        mockMvc.perform(put("/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(responseDto)))
                .andExpect(status().isOk());
    }
}
