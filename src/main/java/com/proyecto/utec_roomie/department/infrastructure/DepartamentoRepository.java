package com.proyecto.utec_roomie.department.infrastructure;


import com.proyecto.utec_roomie.department.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
