package com.proyecto.utec_roomie.Registro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Registro {

    @Id
    private Long id;

}
