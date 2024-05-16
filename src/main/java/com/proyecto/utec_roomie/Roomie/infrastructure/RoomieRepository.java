package com.proyecto.utec_roomie.Roomie.infrastructure;


import com.proyecto.utec_roomie.Estudiante.infrastructure.EstudianteRepository;
import com.proyecto.utec_roomie.Roomie.domain.Roomie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomieRepository extends EstudianteRepository<Roomie> {


}
