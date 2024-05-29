package com.proyecto.utec_roomie.Arrendamiento.dto;

import lombok.Setter;

import java.util.Date;

@Setter
public class ArrendamientoResponseDto {
    private String nombre_roomie;
    private String apellido_roomie;

    private String nombre_anfitrion;
    private String apellido_anfitrion;

    private Date fecha_inicio;
    private Date fecha_fin;
}
