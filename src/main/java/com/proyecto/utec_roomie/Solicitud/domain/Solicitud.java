package com.proyecto.utec_roomie.Invitacion.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Roomie.domain.Roomie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invitacion {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "anfitrion_id", nullable = false)
    Anfitrion anfitrion;

    @ManyToOne
    @JoinColumn(name = "roomie_id", nullable = false)
    Roomie roomie;
}
