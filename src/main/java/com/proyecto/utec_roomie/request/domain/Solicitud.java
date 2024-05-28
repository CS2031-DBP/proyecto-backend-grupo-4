package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String mensaje;

    @Column(nullable = false)
    private SolicitudStatus solicitudStatus = SolicitudStatus.PENDING;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "roomie_id", nullable = false)
    private Roomie roomie;
}
