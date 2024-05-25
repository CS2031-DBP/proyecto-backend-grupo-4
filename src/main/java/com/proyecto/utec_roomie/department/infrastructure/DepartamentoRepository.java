package com.proyecto.utec_roomie.department.infrastructure;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.building.domain.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

    Optional<Departamento> findByEdificioAndNro(Edificio edificio, Integer Nro);
}
