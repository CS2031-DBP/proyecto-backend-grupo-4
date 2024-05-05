package com.proyecto.utec_roomie.Anfitrion.domain;

import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Publicacion.domain.Publicacion;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Anfitrion extends Estudiante {

    @OneToMany(mappedBy = "anfitrion")
    List<Solicitud> invitaciones;

    @OneToOne
    Departamento departamento;

    @OneToOne
    @JoinColumn(name = "publicacion_id")
    Publicacion publicacion;

}
