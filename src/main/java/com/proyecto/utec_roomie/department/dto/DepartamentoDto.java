package com.proyecto.utec_roomie.department.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {
    private Double costo;
    @Column(nullable = false)
    private Integer habitaciones;
    @Column(nullable = false)
    private Integer bano;
    @Column(nullable = false)
    private Float area;
    @Column(nullable = false)
    private String direccion;
}
