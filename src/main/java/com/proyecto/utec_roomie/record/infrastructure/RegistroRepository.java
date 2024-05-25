package com.proyecto.utec_roomie.record.infrastructure;

import com.proyecto.utec_roomie.record.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro,Long> {

}
