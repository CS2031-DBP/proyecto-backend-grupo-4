package com.proyecto.utec_roomie.Anfitrion.dto;

import com.proyecto.utec_roomie.Departamento.dto.DepartamentoDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnfitrionRequestDto {
    @NotNull
    @Size(min = 2)
    private String nombre;

    @NotNull
    @Size(min = 2)
    private String apellido;

    @Email
    private String correo;

    private String descripcion;

    private String carrera;

    @Size(min = 6)
    private String contrasena;

    @Size(min = 9, max = 15)
    @NotNull
    private String telefono;

    private DepartamentoDto departamentoDto;
}
