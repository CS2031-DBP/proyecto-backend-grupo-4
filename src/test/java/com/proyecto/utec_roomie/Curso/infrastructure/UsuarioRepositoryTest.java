package com.proyecto.utec_roomie.Curso.infrastructure;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@ExtendWith({SpringExtension.class, PostgresTestContainer.class})
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setEmail("test@example.com");
        usuario.setRol("inquilino");

        Usuario savedUsuario = usuarioRepository.save(usuario);
        assertNotNull(savedUsuario.getId());
        assertEquals("Test User", savedUsuario.getNombre());
        assertEquals("test@example.com", savedUsuario.getEmail());
        assertEquals("inquilino", savedUsuario.getRol());
    }
}
