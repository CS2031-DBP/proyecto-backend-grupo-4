package com.proyecto.utec_roomie.department.infrastructure;

import com.proyecto.utec_roomie.department.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

    List<Departamento> findAllByCostoLessThanEqual(Double costo);
    List<Departamento> findAllByHabitacionesEquals(Integer habitacion);
    List<Departamento> findAllByPuntajeEquals(Float puntaje);
    List<Departamento> findAllByAreaLessThanEqual(Float area);

    Optional<Departamento> findByLatitudeAndLongitudeAndNro(Double latitude, Double longitude, Integer nro);
}
