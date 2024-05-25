package com.proyecto.utec_roomie.host.domain;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.student.domain.Estudiante;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "anfitrion")

public class Anfitrion extends Estudiante {
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) //CUANDO elimine anfitrion, tambien se elimina el departamento
    @JoinColumn(name = "departamento_id")               //cuando se cree departamento junto anfitrion, se cuardo en contexto algo asixd
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
}
