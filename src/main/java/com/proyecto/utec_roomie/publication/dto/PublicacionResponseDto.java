package com.proyecto.utec_roomie.publication.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionResponseDto {

    private String nombreAnfitrion;
    private String apellidoAnfitrion;

    private String titulo;
    private String descripcion;

    @Column(nullable = false)
    private Integer piso;
    @Column(nullable = false)
    private Double costo;
    @Column(nullable = false)
    private Integer habitaciones;
    @Column(nullable = false)
    private Integer bano;
    @Column(nullable = false)
    private Float area;


}
