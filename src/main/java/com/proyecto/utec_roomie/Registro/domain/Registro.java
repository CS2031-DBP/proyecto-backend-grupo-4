package com.proyecto.utec_roomie.Registro.domain;

import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Registro {

    @Id
    private Long id;

    @OneToOne
    private Estudiante usuario;

    private Date fecha_de_creacion;

    private Date fechaActualizacion;

    private Date ultima_conexion;

}
