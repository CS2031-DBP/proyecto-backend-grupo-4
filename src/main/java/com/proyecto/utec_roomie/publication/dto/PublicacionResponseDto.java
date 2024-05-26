package com.proyecto.utec_roomie.publication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionResponseDto {
    private String titulo;
    private String descripcion;

    private Double costo;
    private Integer habitaciones;

}
