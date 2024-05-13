package com.proyecto.utec_roomie.Roomie.domain;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roomie")
public class Roomie extends Estudiante {

    private String direccion;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Arrendamiento arrendamiento;

    @OneToMany(mappedBy = "roomie", cascade = CascadeType.REMOVE)
    private List<Solicitud> solicitudesEnviadas = new ArrayList<>();

}
