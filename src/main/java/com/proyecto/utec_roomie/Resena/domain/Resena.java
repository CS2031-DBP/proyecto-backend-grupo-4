package com.proyecto.utec_roomie.Resena.domain;

import com.proyecto.utec_roomie.student.domain.Estudiante;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Resena {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Estudiante autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptor_id")
    private Estudiante receptor;

    @Size(max=50)
    private String comentario;

    @Column(nullable = false)
    @DecimalMax("5.0")
    @DecimalMin("0.0")
    private Float puntuacion;
}
