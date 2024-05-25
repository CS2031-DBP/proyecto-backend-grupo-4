package com.proyecto.utec_roomie.publication.domain;

import com.proyecto.utec_roomie.department.domain.Departamento;
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

    private String titulo;
    private String Descripcion;
    private byte[] imagen;
    @OneToOne
//    @JoinColumn(name = "departamento_id")
//    @JoinColumn(name = "anfitrion_id")
//    private Anfitrion anfitrion;
    private Departamento departamento;

    @OneToMany
    private List<Solicitud> solicitud;

}
