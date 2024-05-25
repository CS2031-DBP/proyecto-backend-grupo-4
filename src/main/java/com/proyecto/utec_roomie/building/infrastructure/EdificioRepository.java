package com.proyecto.utec_roomie.building.infrastructure;

import com.proyecto.utec_roomie.building.domain.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    Edificio findByNombre(String nombre);
}
