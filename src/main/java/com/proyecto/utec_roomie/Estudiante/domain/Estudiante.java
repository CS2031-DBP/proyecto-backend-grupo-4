package com.proyecto.utec_roomie.Estudiante.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class Estudiante {
    @Id
    private Long id;

    private String nombre;
    private String apellido;


    @Email
    private String correo;

    private Date fechaNacimiento;

    private String password;

    @Size(min = 9,max = 9)
    private String telefono;

    private String direccion;

    private TipoEstudiante tipoEstudiante;

    private Date fechaCreacion;

    private Date fechaActualizacion;

    @DecimalMax("5.0")
    @DecimalMin("0.0")
    private Double rating;




}
