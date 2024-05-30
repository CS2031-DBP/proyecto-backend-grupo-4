package com.proyecto.utec_roomie.Departamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.utec_roomie.department.application.DepartamentoController;
import com.proyecto.utec_roomie.department.domain.DepartamentoService;
import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartamentoController.class)
public class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Autowired
    private ObjectMapper objectMapper;

    private DepartamentoDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new DepartamentoDto();
        requestDto.setCosto(854.3);
        requestDto.setPiso(2);
        requestDto.setHabitaciones(15);
        requestDto.setBano(5);
        requestDto.setArea(8.0);
        requestDto.setDireccion("1A23B4");
    }

    @Test
    public void testGetDepartamento() throws Exception {
        DepartamentoDto departamentoDto = new DepartamentoDto();
        // Set the properties of departamentoDto as needed

        when(departamentoService.getDepartamento()).thenReturn(departamentoDto);

        mockMvc.perform(get("/departamento/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Adjust jsonPath as per the properties of departamentoDto
    }

    @Test
    public void testActualizaDepartamento() throws Exception {
        DepartamentoDto departamentoDto = new DepartamentoDto();
        // Set the properties of departamentoDto as needed

        mockMvc.perform(patch("/anfitrion/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamentoDto))) // Convert departamentoDto to JSON string
                .andExpect(status().isOk());

        verify(departamentoService, times(1)).anadirDepartamento(any(DepartamentoDto.class));
    }
}

