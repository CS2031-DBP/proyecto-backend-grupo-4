package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "roomie_id", nullable = false)
    Roomie roomie;
}
