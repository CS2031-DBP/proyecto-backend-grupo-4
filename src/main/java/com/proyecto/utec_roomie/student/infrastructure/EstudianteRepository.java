package com.proyecto.utec_roomie.student.infrastructure;

import com.proyecto.utec_roomie.student.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository<T extends Estudiante> extends JpaRepository<T, Long> {
//    Optional<T> findByNombreAndApellidoAndTelefono(String nombre, String apellido, String telefono);
    Optional<T> findByEmail(String email);
}
