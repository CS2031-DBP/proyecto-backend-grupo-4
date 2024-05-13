package com.proyecto.utec_roomie.Anfitrion.domain;

import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Publicacion.domain.Publicacion;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "anfitrion")

public class Anfitrion extends Estudiante {
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) //CUANDO elimine anfitrion, tambien se elimina el departamento
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
}
