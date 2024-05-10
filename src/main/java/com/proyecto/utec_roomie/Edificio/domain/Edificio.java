package com.proyecto.utec_roomie.Edificio.domain;

import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    private List<Departamento> departamentos;



}
