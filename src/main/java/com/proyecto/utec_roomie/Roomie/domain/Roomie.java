package com.proyecto.utec_roomie.Roomie.domain;

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

    @OneToMany(mappedBy = "roomie", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudesEnviadas = new ArrayList<>();

}
