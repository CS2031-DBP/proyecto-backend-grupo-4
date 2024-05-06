package com.proyecto.utec_roomie.Arrendamiento.domain;

import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Arrendamiento {

    @Id
    @GeneratedValue
    private Long id;

    private Date fechaInicio;

    private Date fechaFin;

    @OneToOne
    private Solicitud solicitudOrigen;

}
