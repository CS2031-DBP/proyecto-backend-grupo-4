package com.proyecto.utec_roomie.roomie.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomieResponseDto {
    private String nombre;
    private String apellido;

    private String descripcion;

    @Size(min = 9,max = 9)
    private String telefono;



}
