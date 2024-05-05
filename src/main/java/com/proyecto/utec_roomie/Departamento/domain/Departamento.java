package com.proyecto.utec_roomie.Departamento.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Departamento {
    @Id
    @GeneratedValue
    private Long id;
    private Integer piso;
    private Double costo;
    private Integer habitaciones;
    private String descripcion;

    @OneToOne
    @Valid
    private Anfitrion anfitrion;

}
