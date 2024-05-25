package com.proyecto.utec_roomie.Resena.domain;

import com.proyecto.utec_roomie.student.domain.Estudiante;
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
