package com.proyecto.utec_roomie.host.domain;

import com.proyecto.utec_roomie.department.domain.Departamento;
import com.proyecto.utec_roomie.student.domain.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "anfitrion")
public class Anfitrion extends Users {
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
