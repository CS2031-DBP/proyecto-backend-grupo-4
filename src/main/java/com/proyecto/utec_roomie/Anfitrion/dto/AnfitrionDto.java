package com.proyecto.utec_roomie.Anfitrion.dto;

import com.proyecto.utec_roomie.Departamento.dto.DepartamentoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnfitrionDto {

    private Long id;

    private String descripcion;

    @Size(min = 9,max = 9)
    private String telefono;

    private String direccion;

    @Valid
    private DepartamentoDto departamentoDto;

}
