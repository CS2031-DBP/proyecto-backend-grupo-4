package com.proyecto.utec_roomie.Estudiante.infrastructure;

import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository<T extends Estudiante> extends JpaRepository<T, Long> {

}
