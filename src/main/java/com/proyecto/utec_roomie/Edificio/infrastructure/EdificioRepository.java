package com.proyecto.utec_roomie.Edificio.infrastructure;

import com.proyecto.utec_roomie.Edificio.domain.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    Edificio findByNombre(String nombre);
}
