package com.proyecto.utec_roomie.roomie.domain;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.student.domain.Estudiante;
import com.proyecto.utec_roomie.request.domain.Solicitud;
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

//    @OneToOne(cascade = {CascadeType.REMOVE})
//    private Arrendamiento arrendamiento;
//
//    @OneToMany(mappedBy = "roomie", cascade = {CascadeType.REMOVE})
//    private List<Solicitud> solicitudesEnviadas = new ArrayList<>();

}
