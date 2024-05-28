package com.proyecto.utec_roomie.department.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {
    private Double costo;
    private Integer habitaciones;
    private Integer bano;
    private Double area;
    private String direccion;
}
