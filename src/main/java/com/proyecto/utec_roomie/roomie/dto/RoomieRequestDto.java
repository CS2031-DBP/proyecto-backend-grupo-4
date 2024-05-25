package com.proyecto.utec_roomie.roomie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class RoomieRequestDto {
    @NotNull
    @Size(min = 2)
    private String nombre;

    @NotNull
    @Size(min = 2)
    private String apellido;

    @Email
    private String correo;

    private String descripcion;

    private Date fechaNacimiento;

    private String carrera;

    @Size(min = 6)
    private String contrasena;

    @Size(min = 9, max = 15)
    @NotNull
    private String telefono;
}
