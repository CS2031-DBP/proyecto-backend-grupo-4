package com.proyecto.utec_roomie.Resena.domain;

import com.proyecto.utec_roomie.Anfitrion.domain.Anfitrion;
import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import com.proyecto.utec_roomie.Roomie.domain.Roomie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resena {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Estudiante autor;

    @ManyToOne
    private Estudiante receptor;

    private String comentario;

    @Column(nullable = false)
    private Double puntuacion;
}
