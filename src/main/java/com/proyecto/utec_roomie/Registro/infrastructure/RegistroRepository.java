package com.proyecto.utec_roomie.Registro.infrastructure;

import com.proyecto.utec_roomie.Registro.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro,Long> {

}
