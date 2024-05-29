package com.proyecto.utec_roomie.roomie.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Getter
public class RoomieRequestDto {

    private String descripcion;

    private String carrera;

    private String contrasena;

    private String telefono;
}
