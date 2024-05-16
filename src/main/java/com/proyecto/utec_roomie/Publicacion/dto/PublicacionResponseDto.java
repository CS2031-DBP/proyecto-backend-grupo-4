package com.proyecto.utec_roomie.Publicacion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionResponseDto {
    private String titulo;
    private String descripcion;
    private byte[] imagen; //nose pero este atribute si va xd
}
