package com.proyecto.utec_roomie.student.controller;

import com.proyecto.utec_roomie.student.domain.Estudiante;
import com.proyecto.utec_roomie.student.domain.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private EstudianteService estudianteService;


    private Estudiante estudiante;


    @BeforeEach
    public void setUp() {
        estudiante = new Estudiante();
        estudiante.setNombre("Estudiante");
        estudiante.setApellido("Estudiante");
        estudiante.setEmail("estudiante@gmail.com");
        estudiante.setTelefono("123456789");
        estudiante.setPassword("123456");
        estudiante.setCarrera("Medico");
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estudiante).build();
    }

    @Test
    public void
}
