package com.proyecto.utec_roomie.department.domain;

import com.proyecto.utec_roomie.department.infrastructure.DepartamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service

public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }


    public List<Departamento> getDepartamentos(Double latitudCercana, Double longitudCercana, Double costoMinimo, Float puntajeMinimo, Integer habitaciones, Float areaMinima) {
        return new ArrayList<Departamento>();
    }
}
