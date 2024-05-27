package com.proyecto.utec_roomie.Arrendamiento.infrastructure;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrendamientoRepository extends JpaRepository<Arrendamiento, Long> {
}
