package com.proyecto.utec_roomie.host.dto;

import com.proyecto.utec_roomie.department.domain.Departamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AnfitrionRequestDto {


    private String descripcion;

    private String carrera;

    @Size(min = 6)
    private String contrasena;

    @Size(min = 9, max = 15)
    @NotNull
    private String telefono;
}
