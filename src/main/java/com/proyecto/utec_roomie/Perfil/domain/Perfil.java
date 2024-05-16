package com.proyecto.utec_roomie.Perfil.domain;


import com.proyecto.utec_roomie.Estudiante.domain.Estudiante;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Perfil {

    @Id
    private Long id;

}
