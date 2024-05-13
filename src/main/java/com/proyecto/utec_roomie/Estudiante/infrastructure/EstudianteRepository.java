package com.proyecto.utec_roomie.Estudiante.infrastructure;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository<T extends Estudiante> extends JpaRepository<T, Long> {
    Optional<T> findByNombreAndApellidoAndTelefono(String nombre, String apellido, String telefono);
    Optional<T> findByCorreo(String correo);
}
