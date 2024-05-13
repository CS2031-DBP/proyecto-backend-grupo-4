package com.proyecto.utec_roomie.Estudiante.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)

public class Estudiante {
    @Id
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;

    private String descripcion;

    private String carrera;

    @Email
    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String contrasena;

    @Size(min = 9,max = 9)
    private String telefono;

    @Column(nullable = false)
    private TipoEstudiante tipoEstudiante;

    @DecimalMax("5.0")
    @DecimalMin("0.0")
    private Double rating;

    private Date fecha_de_creacion;

    private Date fechaActualizacion;

    private Date ultima_conexion;

}
