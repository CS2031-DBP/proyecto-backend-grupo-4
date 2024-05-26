package com.proyecto.utec_roomie.publication.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionRequestDto {

    private Long anfitrion_id;
    private String titulo;
    private String descripcion;

}
