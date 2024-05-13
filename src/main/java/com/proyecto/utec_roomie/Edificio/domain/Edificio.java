package com.proyecto.utec_roomie.Edificio.domain;

import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Edificio {
    @Id
    private Long id;
    private String nombre;
    private Integer pisos;

    private String direccion;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Departamento> departamentos;



}
