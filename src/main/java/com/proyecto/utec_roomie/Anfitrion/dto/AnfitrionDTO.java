package com.proyecto.utec_roomie.Anfitrion.dto;

import com.proyecto.utec_roomie.Departamento.domain.Departamento;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnfitrionDTO {

    private Long id;
    private String nombre;
    private String apellido;
    @Email
    private String correo;

    //DepartamentoDto

}
