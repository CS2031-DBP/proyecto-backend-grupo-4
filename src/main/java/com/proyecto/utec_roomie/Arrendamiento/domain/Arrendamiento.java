package com.proyecto.utec_roomie.Reserva.domain;

import com.proyecto.utec_roomie.Invitacion.domain.Invitacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Invitacion invitacionOrigen;

}
