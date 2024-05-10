package com.proyecto.utec_roomie.Departamento.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import jakarta.persistence.*;
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
    private Integer bano;
    private Integer area;

    //no referencia hacia anfitrion y edificio ya que
    // no es necesaria una relacion bidereccional
    //. No es necesaria ya q cada entidad accedera a ella.
    // y tiene su llave foranea y asi puede acceder...
}
