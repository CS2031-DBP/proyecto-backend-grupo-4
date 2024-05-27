package com.proyecto.utec_roomie.Puntaje.insfrastructure;


import com.proyecto.utec_roomie.Puntaje.domain.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntajeRepository extends JpaRepository<Resena, Long> {

}