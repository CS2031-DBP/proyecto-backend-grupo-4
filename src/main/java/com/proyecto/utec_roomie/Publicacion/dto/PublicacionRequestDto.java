package com.proyecto.utec_roomie.Publicacion.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionRequestDto {

    private Long departamento_id;
    private String titulo;
    private String descripcion;

}
