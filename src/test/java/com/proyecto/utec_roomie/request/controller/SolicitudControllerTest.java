package com.proyecto.utec_roomie.request.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.utec_roomie.config.SecurityConfig;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.request.application.SolicitudController;
import com.proyecto.utec_roomie.request.domain.Solicitud;
import com.proyecto.utec_roomie.request.domain.SolicitudService;
import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SolicitudController.class)
@Import(SecurityConfig.class)

public class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolicitudService solicitudService;

    @Autowired
    private ObjectMapper objectMapper;

    private Solicitud solicitud;

    private Publicacion publicacion;
    @Autowired
    private JpaContext jpaContext;

    @BeforeEach
    void setUp() {
        solicitud = new Solicitud();
        solicitud.setId(1L);

        Publicacion publicacion = new Publicacion();
        publicacion.setId(1L);
    }

    @Test
    void testCrearSolicitud() throws Exception {

        mockMvc.perform(post("/solicitudes/{publicacion_id}", publicacion.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Solicitud enviada!"));
    }

//    @Test
//    void testGetSolicitudes() throws Exception {
//        Mockito.when(solicitudService.getSolicitudes()).thenReturn(Collections.singletonList(solicitud));
//
//        mockMvc.perform(get("/solicitudes")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(solicitud.getId()))
//                .andExpect(jsonPath("$[0].fechaCreacion").isNotEmpty());
//    }

    @Test
    void testAceptarSolicitud() throws Exception {
        Long solicitudId = 1L;
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Arrendamiento arrendamiento = new Arrendamiento();

        Mockito.when(solicitudService.aceptarSolicitud(solicitudId, fechaInicio, fechaFin)).thenReturn(arrendamiento);

        mockMvc.perform(post("/solicitudes/{solicitud_id}", solicitudId)
                        .param("fInicio", String.valueOf(fechaInicio.getTime()))
                        .param("fFin", String.valueOf(fechaFin.getTime()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}