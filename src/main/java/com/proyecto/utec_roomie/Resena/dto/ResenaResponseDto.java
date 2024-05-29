package com.proyecto.utec_roomie.Resena.dto;

import lombok.Setter;

@Setter
public class ResenaResponseDto {
    private String nombreAutor;
    private String apellidoAutor;

    private String nombreReceptor;
    private String apellidoReceptor;

    private String comentario;

    private float puntuacion;

}
