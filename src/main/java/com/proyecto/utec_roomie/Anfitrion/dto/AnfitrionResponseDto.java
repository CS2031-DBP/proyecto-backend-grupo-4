package com.proyecto.utec_roomie.Anfitrion.dto;


import com.proyecto.utec_roomie.Departamento.dto.DepartamentoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AnfitrionResponseDto {

    private String nombre;
    private String apellidos;

    private String descripcion;

    @Size(min = 9,max = 9)
    private String telefono;

    @Valid
    private DepartamentoDto departamentoDto;
}
