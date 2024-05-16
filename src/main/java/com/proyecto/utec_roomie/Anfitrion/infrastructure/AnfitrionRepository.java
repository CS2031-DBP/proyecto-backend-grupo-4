package com.proyecto.utec_roomie.Anfitrion.infrastructure;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public interface AnfitrionRepository extends EstudianteRepository<Anfitrion> {


}
