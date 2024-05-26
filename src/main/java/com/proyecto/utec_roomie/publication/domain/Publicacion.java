package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.request.domain.Solicitud;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Publicacion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "anfitrion_id")
    private Anfitrion anfitrion;

    @OneToMany
    private List<Solicitud> solicitudes;

}
