package com.proyecto.utec_roomie.department.domain;

import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.building.domain.Edificio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Departamento {
    @Id
    @GeneratedValue
    private Long id;
    private Integer nro;
    private Integer piso;
    private Double costo;
    private Integer habitaciones;
    private String descripcion;
    private Integer bano;
    private Integer area;

    @ManyToOne
    private Edificio edificio;

    @OneToOne
    private Anfitrion anfitrion;
}
