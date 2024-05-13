package com.proyecto.utec_roomie.Publicacion.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @OneToOne(mappedBy = "publicacion")
    @JoinColumn(name = "departamento_id")
//    @JoinColumn(name = "anfitrion_id")
//    private Anfitrion anfitrion;
    private Departamento departamento;

    @OneToMany(mappedBy = "publicacion")
    private List<Solicitud> solicitud;

}
