package com.proyecto.utec_roomie.host.dto;


import com.proyecto.utec_roomie.department.dto.DepartamentoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AnfitrionResponseDto {

    private String nombre;
    private String apellido;

    private String descripcion;

    @Size(min = 9,max = 9)
    private String telefono;

    @Valid
    private DepartamentoDto departamentoDto;
}
